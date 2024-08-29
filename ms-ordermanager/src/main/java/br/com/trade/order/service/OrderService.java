package br.com.trade.order.service;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import br.com.trade.order.feignclients.CalcFeignClient;
import br.com.trade.order.model.Order;
import br.com.trade.order.model.OrderCopy;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
	@Autowired
	private CalcFeignClient calculadoraClient;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final String TOPIC = "order.in";
	
	public Order getOrder(Long orderId) {
		return new Order("ALGA12", 1.0, 10000, "BUY", "12313213312_13123", LocalTime.now());
	}

	public Double calculate(double quantity, double price) {
		return calculadoraClient.calculatePu(quantity, price).getBody();
	}
	
	public static OrderCopy copyOrderToOrderCopy(Order order) {
        if (order == null) {
            return null;
        }

        OrderCopy orderCopy = new OrderCopy();
        
        orderCopy.setTicker(order.getTicker());
        orderCopy.setClOrdId(order.getClOrdId());
        orderCopy.setPrice(order.getPrice());
        orderCopy.setQuantity(order.getQuantity());
        orderCopy.setPu(order.getPu());
        orderCopy.setTransactTime(order.getTransactTime());
        orderCopy.setSide(order.getSide());

        return orderCopy;
    }
	
	public boolean sendOrder(Order newOrder) {
		try {
			
			OrderCopy copyOrder = copyOrderToOrderCopy(newOrder);
			
			newOrder.setTransactTime(LocalTime.now());
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, copyOrder);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("Order sent to Kafka: {}", copyOrder);
                } else {
                    logger.error("Error sending newOrder to Kafka: {}", ex.getMessage());
                }
            });

            // Block until the future is completed to ensure the result
            future.get();
            return future.isDone() && !future.isCompletedExceptionally();
        } catch (Exception e) {
            logger.error("Error sending Order to Kafka: {}", e.getMessage());
            return false;
        }
	}

}
