package br.com.codeshare.service;

import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.model.Phone;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.vo.PhoneVO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PhoneService implements Serializable{

	@Inject 
	private Logger log;

	@Inject
	private PhoneRepository phoneRepository;

	@Inject
	private Conversor conversor;

	public void register(PhoneVO phone){
		log.info("Registering " + phone.getModel());

		Phone persist = conversor.converter(phone, Phone.class);

		phoneRepository.insert(persist);
	}
	
	public PhoneVO findById(Long id){
		log.info("Search for phone with id " + id);
		return conversor.converter(phoneRepository.findById(id),PhoneVO.class);
	}
	
	public void remove(PhoneVO phoneToBeRemoved){
		log.info(String.format("Removing %s  - %s", phoneToBeRemoved.getBrand(), phoneToBeRemoved.getModel()));
        Phone phone = phoneRepository.findById(phoneToBeRemoved.getId());
        phoneRepository.delete(phone);
	}
	
	public List<PhoneVO> findPhoneByClientId(Long id){
    	return conversor.converter(phoneRepository.findByClientId(id),PhoneVO.class);
    }
}
