package br.com.codeshare.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.model.Phone;

@Stateless
public class PhoneService {

	@Inject 
	private Logger log;
	
	@Inject
	private Event<Phone> phoneEvent;
	
	@Inject
	private PhoneRepository phoneRepository;
	
	public void register(Phone phone){
		log.info("Registering " + phone.getModel());
		phoneRepository.insert(phone);
		phoneEvent.fire(phone);
	}
	
	public Phone findById(Long id){
		log.info("Search for phone with id " + id);
		return phoneRepository.findById(id);
	}
	
	public void remove(Phone phoneToBeRemoved){
		log.info(String.format("Removing %s  - %s", phoneToBeRemoved.getBrand(), phoneToBeRemoved.getModel()));
        Phone phone = phoneRepository.findById(phoneToBeRemoved.getId());
        phoneRepository.delete(phone);
		phoneEvent.fire(phone);
	}
	
	public List<Phone> findPhoneByClientId(Long id){
    	return phoneRepository.findByClientId(id);
    }
}
