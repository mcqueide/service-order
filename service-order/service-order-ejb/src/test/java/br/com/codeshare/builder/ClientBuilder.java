package br.com.codeshare.builder;

import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;

import java.util.List;

public class ClientBuilder {

	private ClientVO client;

	public ClientBuilder() {
		this.client = new ClientVO();
	}
	
	public  ClientBuilder withName (String name){
		client.setName(name);
		return this;
	}
	
	public  ClientBuilder withAdress (String address){
		client.setAddress(address);
		return this;
	}
	
	public  ClientBuilder withHomePhone (String homePhone){
		client.setHomePhone(homePhone);
		return this;
	}
	
	public  ClientBuilder withBusinessPhone (String businessPhone){
		client.setBusinessPhone(businessPhone);
		return this;
	}
	
	public  ClientBuilder withPhone (List<PhoneVO> phones){
		client.setPhones(phones);
		return this;
	}
	
	public ClientBuilder withId(Long id){
		client.setId(id);
		return this;
	}
	
	public ClientVO build(){
		return client;
	}
}
