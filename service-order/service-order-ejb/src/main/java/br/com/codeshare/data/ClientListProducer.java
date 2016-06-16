package br.com.codeshare.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.model.Client;

@RequestScoped
public class ClientListProducer {

	@Inject
	private ClientRepository clientRepository;
	private List<Client> clients;
	
	@Produces
	@Named
	public List<Client> getClients(){
		return clients;
	}
	
	public void onClientListChanged(@Observes(notifyObserver= Reception.IF_EXISTS) final Client client){
		retrieveAllClientsOrderedByName();
	}
	
	@PostConstruct
	public void retrieveAllClientsOrderedByName(){
		clients = clientRepository.findAllOrderedByName();
	}
	
}
