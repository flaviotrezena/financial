package br.com.trade.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

	@Bean
    public NewTopic orderInTopic() {
        return new NewTopic("ORDER.IN", 1, (short) 1);
    }

}
