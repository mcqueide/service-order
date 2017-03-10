package br.com.codeshare.data;

import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.vo.ServiceOrderVO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class ServiceOrderListProducer {
	
	@Inject
	private ServiceOrderRepository serviceOrderRepository;
	private List<ServiceOrderVO> serviceOrders;
	@Inject
	private Conversor conversor;

	@Produces
	@Named
	public List<ServiceOrderVO> getServiceOrders() {
		return serviceOrders;
	}
	
	public void onServiceOrderListChanged(@Observes(notifyObserver=Reception.IF_EXISTS) final ServiceOrder serviceOrder){
		retrieveAllServiceOrder();
	}
	
	@PostConstruct
	public void retrieveAllServiceOrder(){
		serviceOrders = conversor.converter(serviceOrderRepository.findAllOrderedById(),ServiceOrderVO.class);
	}
	
}
