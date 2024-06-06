package br.com.trade.order.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trade.order.model.Order;
import br.com.trade.order.services.OrderService;
import br.com.trade.order.validate.ValidatorData;

@RestController
@RequestMapping(value = "/order")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping(value="/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
		
		Order order = service.getOrder(orderId); 
		
		if (!ValidatorData.isValidOrder(order)) {
			return ResponseEntity.status(401).body(order);
		}
		
		return ResponseEntity.ok(order);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> postOrder(@jakarta.validation.Valid @RequestBody Order order){
		
		
		return ResponseEntity.ok(order.toString());
	}
	
}
