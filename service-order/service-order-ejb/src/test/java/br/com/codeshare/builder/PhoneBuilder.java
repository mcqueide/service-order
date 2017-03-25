package br.com.codeshare.builder;

import br.com.codeshare.enums.PhoneState;
import br.com.codeshare.vo.PhoneVO;

public class PhoneBuilder {

	private PhoneVO phone;

	public PhoneBuilder() {
		this.phone = new PhoneVO();
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

//	public PhoneBuilder withState (ServiceOrderPhone state) {
//		phone.setPhoneStates(state);
//		return this;
//	}

	public PhoneBuilder withEsn (String esn) {
		phone.setEsn(esn);
		return this;
	}

	public PhoneVO buid(){
		return phone;
	}
}
