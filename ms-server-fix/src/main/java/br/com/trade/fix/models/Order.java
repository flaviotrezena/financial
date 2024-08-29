package br.com.trade.fix.models;

import java.time.LocalDateTime;

import quickfix.field.ClOrdID;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;

public class Order {

	private String orderId;
	private String ticker;
	private String clOrderId;
	private double price;
	private int quantity;
	private double pu;
	private int side;
	private LocalDateTime transactTime;

	public Order(String orderId, ClOrdID clOrdID, Symbol symbol, Side side, OrderQty orderQty, Price price2) {
		this.orderId = orderId;
		this.clOrderId = clOrdID.getValue();
		this.ticker = String.valueOf(symbol);
		this.side = Integer.valueOf(side.getValue());
		this.quantity = Double.valueOf(orderQty.getValue()).intValue();
		this.price = price2.getValue();
		this.pu = (this.price * this.quantity);
		this.transactTime = LocalDateTime.now();
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getClOrderId() {
		return clOrderId;
	}

	public void setClOrderId(String clOrderId) {
		this.clOrderId = clOrderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public LocalDateTime getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(LocalDateTime transactTime) {
		this.transactTime = transactTime;
	}
}
