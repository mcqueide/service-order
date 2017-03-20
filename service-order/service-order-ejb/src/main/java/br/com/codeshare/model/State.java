package br.com.codeshare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class State implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_STATE")
	@SequenceGenerator(name="SEQ_STATE",sequenceName="SEQ_STATE",initialValue=1,allocationSize=1)
	@Column(name="state_id")
	private Integer id;
	
	private String situation;
	
	@OneToMany
	private List<PhoneState> phoneStates;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
		
}