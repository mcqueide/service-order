package br.com.codeshare.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "phone_state")
public class PhoneState implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_phone_state")
	@SequenceGenerator(name="seq_phone_state",sequenceName="seq_phone_state",initialValue=1,allocationSize=1)
	@Column(name="phone_state_id")
	private Long id;

	@Column(name = "state")
	private String state;
	
	@OneToMany(mappedBy = "phoneState")
	private List<ServiceOrderPhone> serviceOrderPhones;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String situation) {
		this.state = situation;
	}
		
}