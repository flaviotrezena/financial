package br.com.trader.me.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.trader.me.engine.model.Order;


@Service
public class OrderConsumer {

    @KafkaListener(topics = "ORDER.IN", containerFactory = "kafkaListenerContainerFactory")
    public void consumerOrder(Order order) {
        System.out.println("Consumed message: " + order);
        // Add further processing logic here
    }
}