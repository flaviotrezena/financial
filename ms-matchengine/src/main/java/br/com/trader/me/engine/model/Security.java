package br.com.trader.me.engine.model;

public class Security {

	private long id;
	
	private String ticker;

	private String type;

	private double refPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getRefPrice() {
		return refPrice;
	}

	public void setRefPrice(double refPrice) {
		this.refPrice = refPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Security(long id, String ticker, double refPrice, String type) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.refPrice = refPrice;
		this.type = type;
	}

	
}
