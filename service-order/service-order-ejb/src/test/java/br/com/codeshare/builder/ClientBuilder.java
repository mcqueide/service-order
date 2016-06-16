package br.com.codeshare.builder;

import java.util.List;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;

public class ClientBuilder {

	private Client client;

	public ClientBuilder() {
		this.client = new Client();
	}
	
	public  ClientBuilder withName (String name){
		client.setName(name);
		return this;
	}
	
	public  ClientBuilder withAdress (String adress){
		client.setAdress(adress);
		return this;
	}
	
	public  ClientBuilder withHomePhone (String homePhone){
		client.setHomePhone(homePhone);
		return this;
	}
	
	public  ClientBuilder withBusinessPhone (String businessPhone){
		client.setBisenessPhone(businessPhone);
		return this;
	}
	
	public  ClientBuilder withServiceOrder (List<ServiceOrder> serviceOrders){
		client.setOrdemServicos(serviceOrders);
		return this;
	}
	
	public  ClientBuilder withPhone (List< Phone> phones){
		client.setTelefones(phones);
		return this;
	}
	
	public ClientBuilder withId(Long id){
		client.setId(id);
		return this;
	}
	
	public Client build(){
		return client;
	}
}
