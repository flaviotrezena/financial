package br.com.trader.me.engine.model;

import java.time.LocalTime;

public class Order implements Comparable<Order> {

	private String clOrdId;
	private String ticket;
	private double price;
	private int quantity; 
	private double pu; 
	private String owner;
	private LocalTime transactTime;
	
	public LocalTime getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(LocalTime transactTime) {
		this.transactTime = transactTime;
	}

	public String getOwner() {
		return owner;
	}

	public String getClOrdId() {
		return clOrdId;
	}

	public void setClOrdId(String clOrdId) {
		this.clOrdId = clOrdId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}
	

	public void setOwner(String owner) {
		this.owner = owner;
	}
	

	private String side; //BUY SELL

	public Order(String clOrdId, String ticker, double price, int quantity, String side, String owner) {
		this.clOrdId = clOrdId;
		this.ticket = ticker;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
		this.owner = owner;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public double getPrice() {
		return price;
	}

	@Override
	/**
	 * Check if clOrdId of Order exists in bookOrders
	 */
	public int compareTo(Order o) {
		if (o.clOrdId.equals(this.clOrdId)) {
			return 1;
		}
		return 0;
	}

}
