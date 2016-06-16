package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

@RequestScoped
public class ClientRepository extends AbstractRepository<Client>{

	public List<Client> findClientByName(String name) {
		log.info("Recovering clients ...");
		TypedQuery<Client> query = em.createNamedQuery(Client.FIND_BY_NAME_EAGER, Client.class);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		
		return query.getResultList();
	}
	
	@Override
	public List<Client> findAllOrderedByName() {
		log.info("Recovering clients ...");
		TypedQuery<Client> query = em.createNamedQuery(Client.FIND_ALL,Client.class);
		return query.getResultList();
	}
	
	public void removePhoneClient(Client client, Phone phone){
		log.info(String.format("Removing phone %s belongs %s", phone.getBrand(),client.getName()));
		Client clientMerge = em.merge(client);
		Phone phoneMerge = em.merge(phone);
		clientMerge.getPhones().remove(phoneMerge);
		em.remove(phoneMerge);
	}
	
}
