package br.com.trader.fix.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.trade.fix.models.Order;
import quickfix.Application;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.UnsupportedMessageType;
import quickfix.field.AvgPx;
import quickfix.field.ClOrdID;
import quickfix.field.CumQty;
import quickfix.field.ExecID;
import quickfix.field.ExecType;
import quickfix.field.LeavesQty;
import quickfix.field.MsgType;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.MessageCracker;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;

@Component
public class FixApplication extends MessageCracker implements Application {

	private final KafkaTemplate<String, Order> kafkaTemplate;
	 
	private ConcurrentHashMap<String, LinkedList<Order>> orderBook = new ConcurrentHashMap<>();
	
	 public FixApplication(KafkaTemplate<String, Order> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	 }
	
	public void add(String ticker, Order order) {
        orderBook.compute(ticker, (key, orders) -> {
            if (orders == null) {
                orders = new LinkedList<>();
            }
            orders.add(order);
            return orders;
        });
    }

    public List<Order> get(String ticker) {
        return orderBook.getOrDefault(ticker, new LinkedList<>());
    }
	
	
    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("Session created: " + sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("Logon: " + sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("Logout: " + sessionID);
    }

    @Override
    /*
     * These methods handle send administrative messages (e.g., logon, logout, heartbeat).
     */
    public void toAdmin(Message message, SessionID sessionID) {
        System.out.println("To Admin: " + message);
    }

    
    /*
     * These methods handle received administrative messages (e.g., logon, logout, heartbeat).
     */
    @Override
    public void fromAdmin(Message message, SessionID sessionID) {
        System.out.println("From Admin: " + message);
    }

    /*
     * This method is used to send application-level messages to the counterparty.
     */
    @Override
    public void toApp(Message message, SessionID sessionID) throws quickfix.DoNotSend {
        System.out.println("To App: " + message);
    }

    /*
     * This method is used to receive application-level messages from the counterparty.
     */
    @Override
    public void fromApp(Message message, SessionID sessionID) {
        System.out.println("From App: " + message);
        try {
            String msgType = message.getHeader().getString(MsgType.FIELD);
            if (MsgType.LOGON.equals(msgType)) {
                System.out.println("Received Logon message");
            }else {
            	crack(message, sessionID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onMessage(OrderCancelReplaceRequest orderReplace, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        
    	// Extract order details
        ClOrdID clOrdID = orderReplace.getClOrdID();
        Symbol symbol = orderReplace.getSymbol();
        Side side = orderReplace.getSide();
        OrderQty orderQty = orderReplace.getOrderQty();
        Price price = orderReplace.getPrice();
        
        String orderIdforReplace = orderReplace.getOrderID().getValue();
        
        Order orderInBook = getByOrderId(orderIdforReplace);

        Order updateOrder = new Order(orderIdforReplace, clOrdID, symbol, side, orderQty, price);

        ExecutionReport execReport = new ExecutionReport();

        if (orderInBook!=null) {
        	updateOrder(updateOrder); 
        	
            // Create ExecutionReport
            execReport.set(new OrderID(orderIdforReplace));
            execReport.set(clOrdID);
            execReport.set(new ExecID("54321")); // Unique execution ID
            execReport.set(new ExecType(ExecType.REPLACED)); // Example: Order filled
    		execReport.set(new OrdStatus(OrdStatus.REPLACED)); // Example: Order filled
    		execReport.set(symbol);
    		execReport.set(side);
    		execReport.set(new LeavesQty(0)); // Example: Fully filled
    		execReport.set(new CumQty(orderQty.getValue())); // Example: Fully filled
    		execReport.set(new AvgPx(price.getValue()));

        }else {
        	System.out.println("Nao encontrei a ordem no book, para atualizar");
        	
            // Create ExecutionReport
            execReport.set(new OrderID(orderIdforReplace));
            execReport.set(clOrdID);
            execReport.set(new ExecID("54321")); // Unique execution ID
            execReport.set(new ExecType(ExecType.REJECTED)); // Example: Order filled
    		execReport.set(new OrdStatus(OrdStatus.REJECTED)); // Example: Order filled
    		execReport.set(symbol);
    		execReport.set(side);
    		execReport.set(new LeavesQty(0)); // Example: Fully filled
    		execReport.set(new CumQty(orderQty.getValue())); // Example: Fully filled
    		execReport.set(new AvgPx(price.getValue()));

        }

        //print book
		printBook();
		
        // Send ExecutionReport
        try {
            Session.sendToTarget(execReport, sessionID);            
            
            kafkaTemplate.send("internal.order.in", updateOrder);
            
        } catch (SessionNotFound e) {
            e.printStackTrace();
		}
    }
    
    
    @Override
    public void onMessage(NewOrderSingle order, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        // Extract order details
        ClOrdID clOrdID = order.getClOrdID();
        Symbol symbol = order.getSymbol();
        Side side = order.getSide();
        OrderQty orderQty = order.getOrderQty();
        Price price = order.getPrice();

        String orderId = generateOrderId();         

        Order newOrder = new Order(orderId, clOrdID,symbol,side, orderQty, price);
        add(symbol.getValue().toString(), newOrder); 
        
        // Create ExecutionReport
        ExecutionReport execReport = new ExecutionReport();
        execReport.set(new OrderID(orderId));
        execReport.set(clOrdID);
        execReport.set(new ExecID("54321")); // Unique execution ID
        execReport.set(new ExecType(ExecType.NEW)); // Example: Order filled
		execReport.set(new OrdStatus(OrdStatus.NEW)); // Example: Order filled
		execReport.set(symbol);
		execReport.set(side);
		execReport.set(new LeavesQty(0)); // Example: Fully filled
		execReport.set(new CumQty(orderQty.getValue())); // Example: Fully filled
		execReport.set(new AvgPx(price.getValue()));

        //print book
		printBook();
		
        // Send ExecutionReport
        try {
            Session.sendToTarget(execReport, sessionID);
            
            kafkaTemplate.send("internal.order.in", newOrder);
            
        } catch (SessionNotFound e) {
            e.printStackTrace();
		}
    }

	private String generateOrderId() {
		Random random = new Random();
        long minValue = 1000000000L; // Smallest 10-digit number
        long maxValue = 9999999999L; // Largest 10-digit number
        String orderId = String.valueOf(minValue + (long)(random.nextDouble() * (maxValue - minValue)));
		return orderId;
	}
    
    public void printBook() {
        orderBook.forEach((ticker, orders) -> {
            System.out.println("Ticker: " + ticker);
            for (Order order : orders) {
                System.out.println("    OrderId: " + order.getOrderId() + ", Side: " + (order.getSide() == 1 ? "Compra":"Venda") + ", Quantity: " + order.getQuantity() + ", Price: " + order.getPrice());
            }
        });
    }
    
    
    public Order getByOrderId(String orderId) {
        for (List<Order> orders : orderBook.values()) {
            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null; // Return null if the order is not found
    }
    
    
    public boolean updateOrder(Order updatedOrder) {
        for (List<Order> orders : orderBook.values()) {
            for (Order order : orders) {
                if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                    order.setQuantity(updatedOrder.getQuantity());
                    order.setPrice(updatedOrder.getPrice());
                    // Update other fields as necessary
                    return true; // Order updated successfully
                }
            }
        }
        return false; // Order not found
    }
}