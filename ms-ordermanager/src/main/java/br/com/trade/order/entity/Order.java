package br.com.trade.order.entity;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ticket;
	private double price;
	private double quantity; 
	private int side; //BUY SELL
	
	public int getSide() {
		return side;
	}
	public void setSide(int operacao) {
		this.side = operacao;
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
	public void setPrice(double preco) {
		this.price = preco;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantidade) {
		this.quantity = quantidade;
	}
	
	public Order(String ticket, double preco, double quantidade, int operacao) {
		super();
		this.ticket = ticket;
		this.price = preco;
		this.quantity = quantidade;
		this.side = operacao;
	}
	
	
	
}
