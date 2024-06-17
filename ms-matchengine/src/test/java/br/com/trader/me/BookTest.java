package br.com.trader.me;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import br.com.trader.me.engine.model.Book;
import br.com.trader.me.engine.model.Order;
import br.com.trader.me.engine.model.Trade;

class BookTest {

	@Test
	void testCreateBookWithOrders() {
		
		Book book = new Book();
		
		book.setOrders(createOrders());
		book.setTrades(createTrades());
		
		Assert.isTrue(book.getOrders().size() > 0, "Ordens vazias!");
		Assert.isTrue(book.getTrades().size() > 0, "Trades vazias!");
		
	}
	
	private List<Trade> createTrades() {
		
		List<Trade> mockTrade = new ArrayList<Trade>();
		
		Trade mockTrade1 = new Trade();
		mockTrade1.setTakerOrderId("123_456");
		mockTrade1.setAmount(1000.00);
		mockTrade1.setPrice(5000.00);	
		mockTrade1.setMakerOrderId("XPInvestCor");

		Trade mockTrade2 = new Trade();
		mockTrade2.setTakerOrderId("456_789");
		mockTrade2.setAmount(6000.00);
		mockTrade2.setPrice(30000.00);	
		mockTrade2.setMakerOrderId("AtivaCor");

		mockTrade.add(mockTrade1);
		mockTrade.add(mockTrade2);
		
		return mockTrade;
	}

	private List<Order> createOrders() {
		
		List<Order> mockOrder = new ArrayList<Order>();
		
		Order mockOrder1 = new Order();
		mockOrder1.setPu(2.0);
		mockOrder1.setOwner("XPCOR");
		mockOrder1.setTicket("ALGA12");

		Order mockOrder2 = new Order();
		mockOrder2.setPu(34.0);
		mockOrder1.setOwner("BRADESCOCOR");
		mockOrder2.setTicket("BNDP12");

		mockOrder.add(mockOrder1);
		mockOrder.add(mockOrder2);
		
		return mockOrder;
	}

}
