package br.com.codeshare.enums;

public enum ServiceOrderType {

	BUDGET(0,"new-serviceorder.ostype.budget"),
	SERVICE(1,"new-serviceorder.ostype.service");
	
	private Integer cod;
	private String label;

	private ServiceOrderType(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCodigo() {
		return cod;
	}
	public void setCodigo(Integer codigo) {
		this.cod = codigo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
