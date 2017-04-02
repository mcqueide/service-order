package br.com.codeshare.service;

import br.com.codeshare.data.PhoneStateRepository;
import br.com.codeshare.data.SOPhoneRepository;
import br.com.codeshare.data.ServiceOrderRepository;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.model.ServiceOrderPhone;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.vo.ServiceOrderVO;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ServiceOrderService implements Serializable{

	@Inject
	private ServiceOrderRepository soRepository;

	@Inject
    private SOPhoneRepository soPhoneRepository;

	@Inject
    private PhoneStateRepository phoneStateRepository;
	
	@Inject
	private Event<ServiceOrderVO> soEventSrc;

	@Inject
    private Conversor conversor;
	
    public void register(ServiceOrderVO serviceOrderVO) throws Exception {
        ServiceOrder persist = conversor.converter(serviceOrderVO, ServiceOrder.class);
        soRepository.insert(persist);
        saveSOPhoneState(persist);
        soEventSrc.fire(serviceOrderVO);
    }

    private void saveSOPhoneState(ServiceOrder persist) {
        persist.getSoPhonePhoneState().forEach(soPhoneState -> {
            soPhoneState.setPhoneState(phoneStateRepository.findById(soPhoneState.getPhoneState().getId()));
            soPhoneState.setServiceOrder(persist);
            soPhoneRepository.insert(soPhoneState);
        });
    }

    public List<ServiceOrderVO> findAll(){
    	return conversor.converter(soRepository.findAllOrderedById(),ServiceOrderVO.class);
    }
    
    public ServiceOrderVO find(Long id){
    	return conversor.converter(soRepository.findById(id),ServiceOrderVO.class);
    }
    
    public List<ServiceOrderVO> findClientByName(String name){
    	return conversor.converter(soRepository.findClientByName(name),ServiceOrderVO.class);
    }
    
    public void update(ServiceOrderVO serviceOrder) throws Exception{
        ServiceOrder persist = conversor.converter(serviceOrder, ServiceOrder.class);
        soRepository.update(persist);
        //saveSOPhoneState(persist);
    	soEventSrc.fire(serviceOrder);
    }
    
    public List<ServiceOrderVO> findSoByPhoneId(Long id){
    	return conversor.converter(soRepository.findByPhoneId(id),ServiceOrderVO.class);
    }
}
