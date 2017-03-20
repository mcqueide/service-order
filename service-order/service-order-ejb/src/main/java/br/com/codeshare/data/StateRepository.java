package br.com.codeshare.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.State;

@Stateless
public class StateRepository extends AbstractRepository<State>{
	
	public List<State> findAll(){
		TypedQuery<State> query = getEntityManager().createQuery("Select s from State s", State.class);
		return query.getResultList();
	}
	
}
