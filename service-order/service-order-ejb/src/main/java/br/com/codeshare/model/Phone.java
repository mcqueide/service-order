package br.com.codeshare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@NamedQuery(name = "Phone.findPhoneByClient", query = "select p from Phone p where p.client.id = :clientid")
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_PHONE_BY_CLIENT = "Phone.findPhoneByClient";

	@SequenceGenerator(name = "seq_phone", sequenceName = "seq_phone", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_phone")
	@Column(name = "phone_id")
	private Long id;

	private String brand;

	private String model;

	@OneToMany(mappedBy = "phone")
	private List<ServiceOrderPhone> state;

	private String esn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "phone")
	private List<ServiceOrderPhone> serviceOrderPhones;

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

	public List<ServiceOrderPhone> getState() {
		return state;
	}

	public void setState(List<ServiceOrderPhone> state) {
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

	public List<ServiceOrderPhone> getServiceOrderPhones() {
		return serviceOrderPhones;
	}

	public void setServiceOrderPhones(List<ServiceOrderPhone> serviceOrderPhones) {
		this.serviceOrderPhones = serviceOrderPhones;
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
