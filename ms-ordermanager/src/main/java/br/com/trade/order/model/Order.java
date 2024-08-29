package br.com.trade.order.model;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Order {
	
	
	public Order() {
		super();
	}

	@NotBlank(message = "ticker is mandatory")
    @Size(min = 2, message = "ticker should have at least 2 characters")
	private String ticker;
	
	@NotNull
	private String clOrdId;

	@NotNull
	private String owner;

	@NotNull
	private double price;

	@NotNull
	private int quantity; 

	@NotNull
	private double pu; 

	private LocalTime transactTime;

	@NotBlank
	private String side; //BUY SELL

	public String getClOrdId() {
		return clOrdId;
	}

	public void setClOrdId(String clOrderId) {
		this.clOrdId = clOrderId;
	}

	public LocalTime getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(LocalTime transactTime) {
		this.transactTime = transactTime;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public Order(String ticker, double price, int quantity, String side, String clOrderId, LocalTime transactTime, String owner) {
		this.ticker = ticker;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
		this.clOrdId = clOrderId;
		this.transactTime = transactTime;
		this.owner = owner;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
