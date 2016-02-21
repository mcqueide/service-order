package br.com.codeshare.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.ServiceOrder;

@Stateless
public class ServiceOrderService {

	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<ServiceOrder> serviceOrderEvent;

    public void register(ServiceOrder serviceOrder) throws Exception {
        log.info("Registering " + serviceOrder.getNumberOS());
        em.persist(serviceOrder);
        serviceOrderEvent.fire(serviceOrder);
    }

	public List<ServiceOrder> findClientByName(String nameFilter) {
		TypedQuery<ServiceOrder> query = 
				em.createQuery("from OrdemServico os join fetch os.cliente join fetch os.telefone where lower (os.cliente.nome) like :nome",ServiceOrder.class);
		query.setParameter("nome", "%"+nameFilter.toLowerCase()+"%");
		return query.getResultList();
		
	}
	
	public List<ServiceOrder> findAll() {
		TypedQuery<ServiceOrder> query = 
				em.createQuery("from OrdemServico os join fetch os.cliente join fetch os.telefone",ServiceOrder.class);
		return query.getResultList();
	}
	
	public ServiceOrder find(Long id){
		return em.find(ServiceOrder.class, id);
	}
	
	
}
