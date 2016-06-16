package br.com.codeshare.builder;

import java.util.List;

import br.com.codeshare.enums.PhoneState;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;

public class PhoneBuilder {

	private Phone phone;

	public PhoneBuilder() {
		this.phone = new Phone();
	}

	public PhoneBuilder withId (Long id) {
		phone.setId(id);
		return this;
	}

	public PhoneBuilder withBrand (String brand) {
		phone.setBrand(brand);
		return this;
	}

	public PhoneBuilder withModel (String model) {
		phone.setModel(model);
		return this;
	}

	public PhoneBuilder withState (PhoneState state) {
		phone.setState(state);
		return this;
	}

	public PhoneBuilder withEsn (String esn) {
		phone.setEsn(esn);
		return this;
	}

	public PhoneBuilder withClient (Client client) {
		phone.setClient(client); 
		return this;
	}

	public PhoneBuilder withServiceOrder (List<ServiceOrder> serviceOrders) {
		phone.setOs(serviceOrders);
		return this;
	}
	
	public Phone buid(){
		return phone;
	}
}
