package br.com.trader.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.trader.me")
public class MsMatchengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMatchengineApplication.class, args);
	}

}
