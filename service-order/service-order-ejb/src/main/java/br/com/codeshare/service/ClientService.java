package br.com.codeshare.service;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.enums.ErrorCode;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class ClientService implements Serializable{

	@Inject
	private ClientRepository clientRepository;
	@Inject
	private Event<Client> clientEventSrc;
	@Inject
	private PhoneRepository phoneRepository;
	@Inject
	private ServiceOrderService soService;
    @Inject
    protected Logger log;
    @Inject
	private Conversor conversor;
	@Inject
	private Validator validator;
	
	public void save(ClientVO client) throws Exception{
	    log.info("Registering new cliet: " + client.getName());

		validatePhoneLeastOnePhoneObligatory(client);
		Client persist = conversor.converter(client, Client.class);

		validate(persist);

		clientRepository.insert(persist);
		persist.getPhones().forEach(p -> {
			p.setClient(persist);
			phoneRepository.insert(p);
		});

		clientEventSrc.fire(persist);
	}

	private void validate(Client persist) {
		Set<ConstraintViolation<Client>> violations = validator.validate(persist);

		if(!violations.isEmpty()){
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	public ClientVO findById(Long id)
	{
	    return conversor.converter(clientRepository.findClientById(id),ClientVO.class);
	}
	
	public List<ClientVO> findAll(){
		return conversor.converter(clientRepository.findAllOrderedByName(),ClientVO.class);
	}
	
	public List<ClientVO> findByName(String name) {
		return conversor.converter(clientRepository.findClientByName(name),ClientVO.class);
	}

	public void update(ClientVO client) throws Exception {
        log.info("Updating client: " + client.getName());

		validatePhoneLeastOnePhoneObligatory(client);

		Client persist = conversor.converter(client, Client.class);
		validate(persist);

        registerNewPhones(persist);

		clientRepository.update(persist);

        removePhones(client.getPhonesToBeRemoved());

		clientEventSrc.fire(persist);
	}

    private void registerNewPhones(Client client) throws Exception {
        client.getPhones().forEach(p -> {
			if(p.getId() == null){
				log.info(String.format("Adding new phone (%s-%s) to client (%s)",
						p.getBrand(),p.getModel(),client.getName()));
				p.setClient(client);
				phoneRepository.insert(p);
			}
		});
    }

    private void removePhones(List<PhoneVO> phonesToBeRemove) throws BusinessException {
		if(phonesToBeRemove != null){
			for(PhoneVO phone : phonesToBeRemove){
				if(soService.findSoByPhoneId(phone.getId()).isEmpty()){
					if(phone.getId()!=null){
                        log.info(String.format("Removing phone (%s-%s)",
                                phone.getBrand(),phone.getModel()));
						phoneRepository.delete(phone.getId());//.remove(phone);
					}
				}
				else{
					throw new BusinessException(ErrorCode.PHONE_HAS_SERVICE_ORDER.getErrorCode());
				}
			}
		}
	}

	private void validatePhoneLeastOnePhoneObligatory(ClientVO client) throws BusinessException {
		if(client.getHomePhone().isEmpty() && client.getBusinessPhone().isEmpty()){
			throw new BusinessException(ErrorCode.LEAST_ONE_PHONE_OBLIGATORY.getErrorCode());
		}
	}
	
}
