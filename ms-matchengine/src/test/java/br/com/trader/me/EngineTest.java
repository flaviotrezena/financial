package br.com.trader.me;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.trader.me.engine.Engine;
import br.com.trader.me.engine.model.Order;

class EngineTest {

	@Test
	void testEngineCheckIfOrderExistsFromStart() {
			
		Engine engine = new Engine("CORP");
		engine.start();
		
		List<Order> newTwoOrders = mockOrders();
		
		for (Order newOrder : newTwoOrders) {
			engine.process(newOrder);
		}
		
		assertTrue(engine.getTotalOrders() == newTwoOrders.size());
	}

	
	@Test
	void testEngineCheckMatchOrders() {
			
		Engine engine = new Engine("CORP");
		engine.start();
		
		List<Order> newTwoOrders = mockOrders();
		
		for (Order newOrder : newTwoOrders) {
			engine.process(newOrder);
		}
		
		assertTrue(engine.getTotalTrades() == (newTwoOrders.size()-2));
	}


	private List<Order> mockOrders() {
		
		List<Order> mockOrder = new ArrayList<Order>();
		
		Order mockOrder1 = new Order();
		mockOrder1.setPu(2000.0);
		mockOrder1.setQuantity(1000);
		mockOrder1.setOwner("XPCOR");
		mockOrder1.setClOrdId("123_456");
		mockOrder1.setTicket("ALGA12");
		mockOrder1.setSide("BUY");

		Order mockOrder2 = new Order();
		mockOrder2.setQuantity(5);
		mockOrder2.setPu(34.0);
		mockOrder2.setClOrdId("987_654");
		mockOrder2.setTicket("BNDP12");
		mockOrder2.setOwner("AGORACOR");
		mockOrder2.setSide("SELL");

		Order mockOrder3 = new Order();
		mockOrder3.setQuantity(1000);
		mockOrder3.setPu(2000.0);
		mockOrder3.setOwner("BRADESCO");
		mockOrder3.setClOrdId("456_789");
		mockOrder3.setTicket("ALGA12");
		mockOrder3.setSide("SELL");

		mockOrder.add(mockOrder1);
		mockOrder.add(mockOrder2);
		mockOrder.add(mockOrder3);
		
		return mockOrder;
	}


}
