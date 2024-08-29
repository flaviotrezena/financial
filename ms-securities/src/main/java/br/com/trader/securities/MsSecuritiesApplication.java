package br.com.trader.securities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsSecuritiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSecuritiesApplication.class, args);
	}

}
