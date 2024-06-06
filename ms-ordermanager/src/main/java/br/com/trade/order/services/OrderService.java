package br.com.trade.order.services;

import org.springframework.stereotype.Service;
import br.com.trade.order.entity.Order;

@Service
public class OrderService {

	public Order getOrder(Long orderId) {
		return new Order("ALGA12",1.0,10000,1);
	}

}
