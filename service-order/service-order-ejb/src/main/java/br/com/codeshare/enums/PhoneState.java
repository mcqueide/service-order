package br.com.codeshare.enums;


public enum PhoneState {

	NO_CHIP(0, "phone.state.nochip"), 
	MEMORY_CARD(1, "phone.state.memorycard"), 
	COVER(2,"phone.state.cover"), 
	BATTERY(3, "phone.state.battery");

	private PhoneState(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}

	private Integer cod;
	private String label;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
