package br.com.codeshare.enums;

public enum ServiceOrderState {

	OPEN(0,"Aberto"),
	WAITING_APPROVAL(1,"Aguardando aprovação"),
	APPROVED(2,"Aprovado"),
	COMPLETED(3,"Finalizada");
	
	private Integer codigo;
	private String label;

	private ServiceOrderState(Integer codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
