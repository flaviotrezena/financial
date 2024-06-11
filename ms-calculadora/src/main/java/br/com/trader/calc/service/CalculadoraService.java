package br.com.trader.calc.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CalculadoraService {
	
	@GetMapping(value="/{quantity}/{price}")
	ResponseEntity<Double> calculatePu(@PathVariable double quantity, @PathVariable double price){
	
		return ResponseEntity.ok(Double.valueOf(quantity * price));
		
	}
}
