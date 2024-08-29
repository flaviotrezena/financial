package br.com.trader.me.producer;
/*
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import br.com.trader.me.engine.model.Trade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TradeProducerTmp {

    private static final String TOPIC = "trade.out"; 

    @Autowired
    private KafkaTemplate<String, Trade> kafkaTemplate;

    public TradeProducerTmp(KafkaTemplate<String, Trade> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendTrade(Trade trade) {
    	 CompletableFuture<SendResult<String, Trade>> future = kafkaTemplate.send(TOPIC, trade);
         
         future.whenComplete((result, ex) -> {
             if (ex == null) {
                 log.info("Sent order: {}", trade);
             } else {
                 log.error("Failed to send order: {}", trade, ex);
             }
         });
    }
}
*/