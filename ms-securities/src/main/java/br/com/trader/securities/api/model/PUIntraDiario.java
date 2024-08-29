package br.com.trader.securities.api.model;

public class PUIntraDiario {

	private String codigo_selic;
	private String data_referencia;
	private String data_vencimento;
	private double pu;
	private double taxa_intradiaria;
	private String tipo_pu;
	private String tipo_titulo;
	
	public String getCodigo_selic() {
		return codigo_selic;
	}
	public void setCodigo_selic(String codigo_selic) {
		this.codigo_selic = codigo_selic;
	}
	public String getData_referencia() {
		return data_referencia;
	}
	public void setData_referencia(String data_referencia) {
		this.data_referencia = data_referencia;
	}
	public String getData_vencimento() {
		return data_vencimento;
	}
	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}
	public double getPu() {
		return pu;
	}
	public void setPu(double pu) {
		this.pu = pu;
	}
	public double getTaxa_intradiaria() {
		return taxa_intradiaria;
	}
	public void setTaxa_intradiaria(double taxa_intradiaria) {
		this.taxa_intradiaria = taxa_intradiaria;
	}
	public String getTipo_pu() {
		return tipo_pu;
	}
	public void setTipo_pu(String tipo_pu) {
		this.tipo_pu = tipo_pu;
	}
	public String getTipo_titulo() {
		return tipo_titulo;
	}
	public void setTipo_titulo(String tipo_titulo) {
		this.tipo_titulo = tipo_titulo;
	}
	
	
	
	
}
