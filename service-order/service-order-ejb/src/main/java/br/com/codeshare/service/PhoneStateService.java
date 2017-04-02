package br.com.codeshare.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.codeshare.data.PhoneStateRepository;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.vo.PhoneStateVO;

@Stateless
public class PhoneStateService implements Serializable{
	
	@Inject
	private PhoneStateRepository phoneStateRepository;

	@Inject
	private Conversor conversor;
	
	public List<PhoneStateVO> findAll(){
		return conversor.converter(phoneStateRepository.findAll(), PhoneStateVO.class);
	}

	public PhoneStateVO findById(Long id){
		return conversor.converter(phoneStateRepository.findById(id),PhoneStateVO.class);
	}
	
	
}
