package br.com.codeshare.service;

import java.util.List;

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
	
	public void save(Client client) throws Exception{
		
		validatePhoneLeastOnePhoneObligatory(client);
		validatePhoneLength(client);
		
		clientRepository.insert(client);
		clientEventSrc.fire(client);
	}

	private void validatePhoneLength(Client client) throws BusinessException {
		if(!client.getHomePhone().isEmpty()){
			if(client.getHomePhone().length() < 13 || client.getHomePhone().length() >14){
				throw new BusinessException(ErrorCode.LENGTH_MUST_BE_BETWEEN_13_14.getErrorCode());
			}
		}
		if(!client.getBisenessPhone().isEmpty()){
			if(client.getBisenessPhone().length() < 13 || client.getBisenessPhone().length() >14){
				throw new BusinessException(ErrorCode.LENGTH_MUST_BE_BETWEEN_13_14.getErrorCode());
			}
		}
	}

	private void validatePhoneLeastOnePhoneObligatory(Client client) throws BusinessException {
		if(client.getHomePhone().isEmpty() && client.getBisenessPhone().isEmpty()){
			throw new BusinessException(ErrorCode.LEAST_ONE_PHONE_OBLIGATORY.getErrorCode());
		}
	}
	
	public Client findById(Long id)
	{
		return clientRepository.findById(id);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAllOrderedByName();
	}
	
	public List<Client> findByName(String name) {
		return clientRepository.findClientByName(name);
	}

	public void update(Client client,List<Phone>phonesToBeRemove) throws Exception {
		
		validatePhoneLeastOnePhoneObligatory(client);
		validatePhoneLength(client);
		
		clientRepository.update(client);
		
		if(phonesToBeRemove != null){
			for(Phone phone : phonesToBeRemove){
				if(soService.findSoByPhoneId(phone.getId()).isEmpty()){
					phoneService.remove(phone);
				}
				else{
					throw new BusinessException(ErrorCode.PHONE_HAS_SERVICE_ORDER.getErrorCode());
				}
			}
		}
		
		clientEventSrc.fire(client);
	}
	
	public void removePhoneCliente(Client client, Phone phone){
		clientRepository.removePhoneClient(client, phone);
	}
	
}
