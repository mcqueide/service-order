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
		TypedQuery<Client> query = getEntityManager().createNamedQuery(Client.FIND_BY_NAME_EAGER, Client.class);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		
		return query.getResultList();
	}
	
	@Override
	public List<Client> findAllOrderedByName() {
		log.info("Recovering clients ...");
		TypedQuery<Client> query = getEntityManager().createNamedQuery(Client.FIND_ALL,Client.class);
		return query.getResultList();
	}
	
	public void removePhoneClient(Client client, Phone phone){
		log.info(String.format("Removing phone %s belongs %s", phone.getBrand(),client.getName()));
		Client clientMerge = getEntityManager().merge(client);
		Phone phoneMerge = getEntityManager().merge(phone);
		clientMerge.getPhones().remove(phoneMerge);
		getEntityManager().remove(phoneMerge);
	}

	public Client findClientById(Long id){
		return getEntityManager().createQuery("select client from Client client join fetch client.phones where client.id = :id", Client.class)
				.setParameter("id",id).getSingleResult();
	}
	
}
