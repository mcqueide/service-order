package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Phone;

@RequestScoped
public class PhoneRepository extends AbstractRepository<Phone>{

	public List<Phone> findByClientId(Long id) {
		log.info(String.format("Recovering phone of client: %d",id));
		TypedQuery<Phone> query =
				getEntityManager().createNamedQuery(Phone.FIND_PHONE_BY_CLIENT,Phone.class);
		query.setParameter("clientid", id);
		return query.getResultList();
	}

}
