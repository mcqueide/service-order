package br.com.codeshare.enums;

public enum ServiceOrderState {

	OPEN(0,"update.serviceorder.osstate.open"),
	WAITING_APPROVAL(1,"update.serviceorder.osstate.waitapproval"),
	APPROVED(2,"update.serviceorder.osstate.approved"),
	COMPLETED(3,"update.serviceorder.osstate.finalized");
	
	private Integer id;
	private String label;

	private ServiceOrderState(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
