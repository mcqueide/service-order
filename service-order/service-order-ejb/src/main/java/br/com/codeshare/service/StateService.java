package br.com.codeshare.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.codeshare.data.StateRepository;
import br.com.codeshare.model.State;

@Stateless
public class StateService {
	
	@Inject
	private StateRepository stateRepository;
	
	public List<State> findAll(){
		return stateRepository.findAllOrderedById();
	}
	
	
}
