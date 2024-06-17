package br.com.trade.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Order {
	
	@NotBlank(message = "ticket is mandatory")
    @Size(min = 2, message = "ticket should have at least 2 characters")
	private String ticket;
	
	@NotNull
	private String clOrderId;

	public String getClOrderId() {
		return clOrderId;
	}

	public void setClOrderId(String clOrderId) {
		this.clOrderId = clOrderId;
	}

	@NotNull
	private double price;

	@NotNull
	private double quantity; 

	@NotNull
	private double pu; 

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	@NotBlank
	private String side; //BUY SELL

	public Order(String ticker, double price, int quantity, String side, String clOrderId) {
		this.ticket = ticker;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
		this.clOrderId = clOrderId;
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

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
	
	
}
