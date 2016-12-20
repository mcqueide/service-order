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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.codeshare.enums.PhoneState;

@Entity
@NamedQuery(name="Phone.findPhoneByClient", query="select p from Phone p where p.client.id = :clientid")
public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String FIND_PHONE_BY_CLIENT = "Phone.findPhoneByClient";
	
	@SequenceGenerator(name="SEQ_PHONE",sequenceName="SEQ_PHONE",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PHONE")
	@Column(name="phone_id")
	private Long id;
	
	private String brand;
	
	private String model;
	
	@Column(name="phone_state")
	@Enumerated(EnumType.ORDINAL)
	private PhoneState state;
	
	private String esn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
	
	@OneToMany(mappedBy="phone")
	private List<ServiceOrder> os;
	
	public Phone() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
