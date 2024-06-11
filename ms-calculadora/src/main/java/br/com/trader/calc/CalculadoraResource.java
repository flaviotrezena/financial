package br.com.trader.calc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calc")
public class CalculadoraResource {

	@GetMapping(value="/{quantity}/{price}")
	ResponseEntity<Double> calculatePu(@PathVariable double quantity, @PathVariable double price){
	
		return ResponseEntity.ok(Double.valueOf(quantity * price));
		
	}
}
