package br.com.trade.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "ms-calculadora", url="localhost:8001", path="/calc")
public interface CalcFeignClient {

	@GetMapping(value="/{quantity}/{price}")
	ResponseEntity<Double> calculatePu(@PathVariable double quantity, @PathVariable double price);
		
}
