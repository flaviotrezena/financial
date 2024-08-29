package br.com.trader.me.engine.model;

import java.time.LocalDateTime;

public class Trade {

	private long id;

	private String takerOrderId;

	private String makerOrderId;

	private double amount;

	private double price;

	private int quantity;

	private String side;

	private String ticker;

	private LocalDateTime transactTime;

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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public LocalDateTime getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(LocalDateTime transactTime) {
		this.transactTime = transactTime;
	}
	
	

	/**
	 * Create an instance of Trade.
	 *
	 * @param pTakerOrderId
	 * @param pMakerOrderId
	 * @param pAmount
	 * @param pPrice
	 */
	public Trade(long id, final String pTakerOrderId, final String pMakerOrderId, final double pAmount,
			final double pPrice, int quantity, String side, String ticker) {
		super();
		this.id = id;
		this.takerOrderId = pTakerOrderId;
		this.makerOrderId = pMakerOrderId;
		this.amount = pAmount;
		this.price = pPrice;
		this.quantity = quantity;
		this.side = side;
		this.ticker = ticker;
	}

	public Trade() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the takerOrderId
	 */
	public String getTakerOrderId() {
		return this.takerOrderId;
	}

	/**
	 * @param pTakerOrderId the takerOrderId to set
	 */
	public void setTakerOrderId(final String pTakerOrderId) {
		this.takerOrderId = pTakerOrderId;
	}

	/**
	 * @return the makerOrderId
	 */
	public String getMakerOrderId() {
		return this.makerOrderId;
	}

	/**
	 * @param pMakerOrderId the makerOrderId to set
	 */
	public void setMakerOrderId(final String pMakerOrderId) {
		this.makerOrderId = pMakerOrderId;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * @param pAmount the amount to set
	 */
	public void setAmount(final double pAmount) {
		this.amount = pAmount;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * @param pPrice the price to set
	 */
	public void setPrice(final double pPrice) {
		this.price = pPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
