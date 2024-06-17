package br.com.trader.me.engine.model;

public class Security {

	private long id;
	
	private String ticket;

	private String type;

	private double refPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
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

	public Security(long id, String ticket, double refPrice, String type) {
		super();
		this.id = id;
		this.ticket = ticket;
		this.refPrice = refPrice;
		this.type = type;
	}

	
}
