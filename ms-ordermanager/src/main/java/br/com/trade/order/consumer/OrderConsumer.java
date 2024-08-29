package br.com.trade.order.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.trade.order.model.Order;

@Service
public class OrderConsumer {


    @KafkaListener(topics = "internal.order.in", containerFactory = "kafkaListenerContainerFactory")
    public void consumerOrder(Order order) {
        System.out.println("Consumed message: " + order);
        // Add further processing logic here
    }
}
