package br.com.codeshare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.codeshare.enums.PhoneState;

@Entity
public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="SEQ_PHONE",sequenceName="SEQ_PHONE",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PHONE")
	@Column(name="telefone_id")
	private int id;
	private String brand;
	private String model;
	@Column(name="phone_state")
	@Enumerated(EnumType.ORDINAL)
	private PhoneState state;
	private String esn;
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	@OneToMany(mappedBy="phone")
	private List<ServiceOrder> os;
	
	public Phone() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public PhoneState getState() {
		return state;
	}
	public void setState(PhoneState state) {
		this.state = state;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ServiceOrder> getOs() {
		return os;
	}
	public void setOs(List<ServiceOrder> os) {
		this.os = os;
	}
	
}
