package br.com.codeshare.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Client;

@Stateless
public class ClientService {

	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Inject
    private Event<Client> clientEvent;
	
	public void save(Client client) throws Exception{
		log.info("Saving " + client.getName());
		em.persist(client);
		clientEvent.fire(client);
	}
	
	public List<Client> searchAll(){
		log.info("Search for all clients");
		TypedQuery<Client> query = 
				em.createQuery("from Client cl join fetch cl.telefones",Client.class);
		return query.getResultList();
	}
	
}
