package br.com.trader.dropcopy.listener;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.trader.dropcopy.model.Trade;
import quickfix.FieldNotFound;
import quickfix.Initiator;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.AvgPx;
import quickfix.field.CumQty;
import quickfix.field.ExecType;
import quickfix.field.LeavesQty;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TradeDate;
import quickfix.fix44.ExecutionReport;

@Service
public class OrderListenerService {

    @Autowired
    private Initiator initiator;

    @KafkaListener(topics = "TRADE.OUT", groupId = "group_id")
    public void listen(Trade trade) throws FieldNotFound {
        System.out.println("Generating Trade: " + trade + " and sending to FIX session.");

        try {
            // Construct the FIX message
        	 ExecutionReport executionReport = new ExecutionReport();
        	 executionReport.set (new Symbol(trade.getTicker()));
        	 executionReport.set(new ExecType(ExecType.FILL));
        	 executionReport.set(new OrdStatus(OrdStatus.FILLED));
        	 executionReport.set(new OrderID(String.valueOf(trade.getId())));
        	 if ("BUY".equals(executionReport.getSide())){
            	 executionReport.set(new Side(Side.BUY));
        	 }else {
            	 executionReport.set(new Side(Side.SELL));
        	 }
        	 executionReport.set(new CumQty(trade.getQuantity()));
        	 executionReport.set(new AvgPx(trade.getPrice()));
        	 executionReport.set(new LeavesQty(0));
        	 executionReport.set(new TradeDate(trade.getTransactTime().format(DateTimeFormatter.ISO_DATE_TIME)));
            
            // Get the active session
            SessionID sessionID = initiator.getSessions().get(0); // assuming single session
            Session.sendToTarget(executionReport, sessionID);
        } catch (SessionNotFound e) {
            e.printStackTrace();
        }
    }
}
