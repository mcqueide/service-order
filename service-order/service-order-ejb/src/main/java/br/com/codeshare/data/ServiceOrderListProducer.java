package br.com.codeshare.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.model.ServiceOrder;

import java.util.List;

@RequestScoped
public class ServiceOrderListProducer {
	
	@Inject
	private ServiceOrderRepository serviceOrderRepository;
	private List<ServiceOrder> serviceOrders;

	@Produces
	@Named
	public List<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}
	
	public void onServiceOrderListChanged(@Observes(notifyObserver=Reception.IF_EXISTS) final ServiceOrder serviceOrder){
		retrieveAllServiceOrder();
	}
	
	@PostConstruct
	public void retrieveAllServiceOrder(){
		serviceOrders = serviceOrderRepository.findAllOrderedById();
	}
	
}
