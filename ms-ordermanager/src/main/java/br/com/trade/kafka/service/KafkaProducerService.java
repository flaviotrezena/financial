package br.com.trade.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.trade.order.model.Order;

@Service
public class KafkaProducerService {

	private static final String TOPIC = "orders";

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Order order) {
		kafkaTemplate.send(TOPIC, order);
	}
}
