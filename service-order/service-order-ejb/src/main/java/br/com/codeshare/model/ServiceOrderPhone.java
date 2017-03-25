package br.com.codeshare.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "so_phone_phone_state")
public class ServiceOrderPhone implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_phone_phone_state")
	@SequenceGenerator(name="seq_phone_phone_state",sequenceName="seq_phone_phone_state",initialValue=1,allocationSize=1)
	@Column(name="phone_phone_state_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="phone_state_id")
	private PhoneState phoneState;

	@ManyToOne
	@JoinColumn(name="phone_id")
	@NotNull(message="{phone.notempty}")
	private Phone phone;

	@ManyToOne
	@JoinColumn(name="so_id")
	private ServiceOrder serviceOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhoneState getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(PhoneState phoneState) {
		this.phoneState = phoneState;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
}