package br.com.trade.order.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;


public class OrderCopy extends Order {
	
	@JsonProperty("ticker")
	private String ticker;
	
	@JsonProperty("clOrdId")
	private String clOrdId;

	@JsonProperty("price")
	private double price;

	@JsonProperty("quantity")
	private double quantity; 

	@JsonProperty("pu")
	private double pu; 

	@JsonProperty("transactTime")
	private LocalTime transactTime;

	@JsonProperty("owner")
	private String owner;
	

	@NotBlank
	private String side; //BUY SELL

	public OrderCopy(String ticker, double price, int quantity, String side, String clOrderId, LocalTime transactTime, String owner) {
		super(ticker,price,quantity,side,clOrderId, transactTime, owner);
		this.ticker = ticker;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
		this.clOrdId = clOrderId;
		this.transactTime = transactTime;
		this.owner = owner;
	}

	public OrderCopy() {
		// TODO Auto-generated constructor stub
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

	public void setQuantity(double quantity) {
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

	public String getClOrdId() {
		return clOrdId;
	}

	public void setClOrdId(String clOrdId) {
		this.clOrdId = clOrdId;
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
	
}
