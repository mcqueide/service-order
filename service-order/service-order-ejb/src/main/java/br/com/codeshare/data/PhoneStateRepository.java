package br.com.codeshare.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.PhoneState;

@Stateless
public class PhoneStateRepository extends AbstractRepository<PhoneState>{
	
	public List<PhoneState> findAll(){
		TypedQuery<PhoneState> query = getEntityManager().createQuery("SELECT s FROM PhoneState s", PhoneState.class);
		return query.getResultList();
	}
	
}
