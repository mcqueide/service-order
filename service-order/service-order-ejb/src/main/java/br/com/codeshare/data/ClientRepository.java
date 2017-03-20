package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Client;

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
	
	public Client findClientById(Long id){
		log.info(String.format("Recovering client (id=%d) and your phones: ",id));
		return getEntityManager().createQuery("select client from Client client left join fetch client.phones where client.id = :id", Client.class)
				.setParameter("id",id).getSingleResult();
	}
	
}
