package br.com.trade.order.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trade.order.entity.Order;
import br.com.trade.order.services.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping(value="/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
		
		Order order = service.getOrder(orderId); 
		return ResponseEntity.ok(order);
	}
	
}
