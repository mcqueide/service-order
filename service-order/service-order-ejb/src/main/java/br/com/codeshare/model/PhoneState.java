package br.com.codeshare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class PhoneState {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PHONE_STATE")
	@SequenceGenerator(name="SEQ_PHONE_STATE",sequenceName="SEQ_STATE",initialValue=1,allocationSize=1)
	@Column(name="phone_state_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name="phone_id")
	private Phone phone;
}