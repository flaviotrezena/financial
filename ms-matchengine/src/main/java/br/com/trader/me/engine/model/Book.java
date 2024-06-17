package br.com.trader.me.engine.model;

import java.util.List;

public class Book {

	private List<Order> orders;
	
	private List<Trade> trades;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
	
	
	
	
}
