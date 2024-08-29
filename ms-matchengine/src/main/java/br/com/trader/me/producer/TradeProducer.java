package br.com.trader.me.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import br.com.trader.me.engine.model.Trade;

@Service
public class TradeProducer {

	 private static final String TOPIC = "trade.out";

	    @Autowired
	    private KafkaTemplate<String, Trade> kafkaTemplate;

	    public void sendMessage(Trade trade) {
	        kafkaTemplate.send(TOPIC, trade);
	    }
	    
}
