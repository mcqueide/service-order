package br.com.codeshare.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.enums.ErrorCode;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

@Stateless
public class ClientService{

	@Inject
	private ClientRepository clientRepository;
	@Inject
	private Event<Client> clientEventSrc;
	@Inject
	private PhoneService phoneService;
	@Inject
	private ServiceOrderService soService;
    @Inject
    protected Logger log;
	
	public void save(Client client) throws Exception{
	    log.info("Registering new cliet: " + client.getName());

		validatePhoneLeastOnePhoneObligatory(client);

		clientRepository.insert(client);

		for(Phone phone : client.getPhones()){
			phoneService.register(phone);
		}

		clientEventSrc.fire(client);
	}

	public Client findById(Long id)
	{
	    return clientRepository.findClientById(id);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAllOrderedByName();
	}
	
	public List<Client> findByName(String name) {
		return clientRepository.findClientByName(name);
	}

	public void update(Client client,List<Phone>phonesToBeRemove) throws Exception {
        log.info("Updating client: " + client.getName());

		validatePhoneLeastOnePhoneObligatory(client);

		clientRepository.update(client);

        registerNewPhones(client);

        removePhones(phonesToBeRemove);

		clientEventSrc.fire(client);
	}

    private void registerNewPhones(Client client) throws Exception {
        for (Phone phone : client.getPhones()) {
            if(phone.getId() == null){
                log.info(String.format("Adding new phone (%s-%s) to client (%s)",
                        phone.getBrand(),phone.getModel(),client.getName()));
                phoneService.register(phone);
            }
        }
    }

    private void removePhones(List<Phone> phonesToBeRemove) throws BusinessException {
		if(phonesToBeRemove != null){
			for(Phone phone : phonesToBeRemove){
				if(soService.findSoByPhoneId(phone.getId()).isEmpty()){
					if(phone.getId()!=null){
                        log.info(String.format("Removing phone (%s-%s)",
                                phone.getBrand(),phone.getModel()));
						phoneService.remove(phone);
					}
				}
				else{
					throw new BusinessException(ErrorCode.PHONE_HAS_SERVICE_ORDER.getErrorCode());
				}
			}
		}
	}

	private void validatePhoneLeastOnePhoneObligatory(Client client) throws BusinessException {
		if(client.getHomePhone().isEmpty() && client.getBisenessPhone().isEmpty()){
			throw new BusinessException(ErrorCode.LEAST_ONE_PHONE_OBLIGATORY.getErrorCode());
		}
	}
	
}
