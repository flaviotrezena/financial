package br.com.trade.order.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import br.com.trade.order.feignclients.CalcFeignClient;
import br.com.trade.order.model.Order;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
	@Autowired
	private CalcFeignClient calculadoraClient;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final String TOPIC = "order";
	
	public Order getOrder(Long orderId) {
		return new Order("ALGA12", 1.0, 10000, "BUYER", "12313213312_13123");
	}

	public Double calculate(double quantity, double price) {
		return calculadoraClient.calculatePu(quantity, price).getBody();
	}
	
	public boolean sendOrder(Order newOrder) {
		try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, newOrder);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("newOrder sent to Kafka: {}", newOrder);
                } else {
                    logger.error("Error sending newOrder to Kafka: {}", ex.getMessage());
                }
            });

            // Block until the future is completed to ensure the result
            future.get();
            return future.isDone() && !future.isCompletedExceptionally();
        } catch (Exception e) {
            logger.error("Error sending user to Kafka: {}", e.getMessage());
            return false;
        }
	}

}
