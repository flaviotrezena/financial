package br.com.trade.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsOrderManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOrderManagerApplication.class, args);
	}

}
