package br.com.trader.me.engine.model;

import java.time.LocalDateTime;

public class Trade {

	 /**
     * Taker's order ID.
     */
    private String takerOrderId;

    /**
     * Maker's order ID.
     */
    private String makerOrderId;

    /**
     * Trade amount.
     */
    private double amount;

    /**
     * Trade price.
     */
    private double price;

    
    private LocalDateTime transactTime;

    
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
    public Trade(final String pTakerOrderId, final String pMakerOrderId,
            final double pAmount, final double pPrice) {
        super();
        this.takerOrderId = pTakerOrderId;
        this.makerOrderId = pMakerOrderId;
        this.amount = pAmount;
        this.price = pPrice;
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
    
}
