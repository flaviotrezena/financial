package br.com.trade.order.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trade.order.feignclients.CalcFeignClient;
import br.com.trade.order.model.Order;
import br.com.trade.order.service.OrderService;
import br.com.trade.order.validate.ApiResponse;

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
	
	@PostMapping(value = "/add")
	public ResponseEntity<ApiResponse<String>> postOrder(@jakarta.validation.Valid @RequestBody Order order){

		try {
			
			double orderPU = service.calculate(order.getQuantity(), order.getPrice());  
			
			order.setPu(orderPU);
			
            boolean isSuccess = service.sendOrder(order);
            if (isSuccess) {
                return new ResponseEntity<>(new ApiResponse<>(true, "User is valid and message sent to Kafka", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse<>(false, "Failed to send message to Kafka", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}
