package br.com.trader.me.consumer;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.trader.me.engine.Engine;
import br.com.trader.me.engine.model.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderConsumer {
	
	private Engine engine = new Engine("CORP");
	private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order.in", groupId = "order-id-consumer-group")
    public void consume(ConsumerRecord<String, Order> record) {
		log.info("consuming Order from kafka");
        Order orderMsg = record.value();
        
        try {
            // Deserialize the JSON string into an Order object
            //Order order = objectMapper.readValue(message, Order.class);

            processMessage(orderMsg);
            
            log.info("Order received: {}", orderMsg);
        } catch (Exception e) {
            // Handle the exception (logging, retry logic, etc.)
            log.error("Error deserializing message: {}", orderMsg, e);
        }
    }

    private Order processMessage(Order newOrder) {
    	try {
    		engine.process(newOrder);
    		newOrder.setProcessedByMatchEngine(LocalDateTime.now());
    		return newOrder;
    	}catch(Exception e) {
    		log.error("Error on processing Order {}", newOrder.getClOrdId(), e);
    		return null;
    	}
    }
}