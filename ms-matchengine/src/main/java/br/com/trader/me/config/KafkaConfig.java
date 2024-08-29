package br.com.trader.me.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic tradeOutTopic() {
        return new NewTopic("trade.out", 1, (short) 1);
    }

}