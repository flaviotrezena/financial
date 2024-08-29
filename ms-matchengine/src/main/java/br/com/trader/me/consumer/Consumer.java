package br.com.trader.me.consumer;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.trader.me.engine.Engine;
import br.com.trader.me.engine.model.Order;

@Service
public class Consumer {
	
	private Engine engine = new Engine("CORP");
	private static final String ORDER_OUT_TOPIC = "order.out";
    private final KafkaTemplate<String, Order> kafkaTemplate;
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    
    //engine.start();
	public Consumer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order.in", groupId = "order-id-consumer-group")
    public void consume(ConsumerRecord<String, String> record) {
		logger.info("consuming Order from kafka");
        String message = record.value();
        
        try {
            // Deserialize the JSON string into an Order object
            Order order = objectMapper.readValue(message, Order.class);

            processMessage(order);
            this.kafkaTemplate.send(ORDER_OUT_TOPIC, order);

            logger.info("Order received: " + order);
        } catch (Exception e) {
            // Handle the exception (logging, retry logic, etc.)
            logger.error("Error deserializing message: " + e.getMessage());
        }
    }

    private Order processMessage(Order newOrder) {
    	try {
    		engine.process(newOrder);
    		newOrder.setProcessedByMatchEngine(LocalDateTime.now());
    		return newOrder;
    	}catch(Exception e) {
    		logger.error("Error on processing Order "+newOrder.getClOrdId());
    		return null;
    	}
    }
}