package br.com.trader.me.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private static final String OUTPUT_TOPIC = "TRADE.OUT";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Consumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "ORDER.IN", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> record) {
        String message = record.value();
        // Process the message and produce to the next topic
        this.kafkaTemplate.send(OUTPUT_TOPIC, processMessage(message));
    }

    private String processMessage(String message) {
        // Implement your processing logic here
        return "Processed: " + message;
    }
}