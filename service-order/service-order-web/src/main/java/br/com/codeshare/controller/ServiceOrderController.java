package br.com.codeshare.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.service.ServiceOrderService;

@Model
public class ServiceOrderController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private ServiceOrderService serviceOrderService;

	private ServiceOrder newServiceOrder;
	private String filterClient;
	private Long filterSo;
	private List<ServiceOrder> listServiceOrder;
	private ServiceOrderType[] orderTypes;

	@Produces
	@Named
	public ServiceOrder getNewServiceOrder() {
		return newServiceOrder;
	}

	public void save() throws Exception {
		try {
			serviceOrderService.register(newServiceOrder);
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Registered!",
					"Registration successful"));
			initNewServiceOrder();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	@PostConstruct
	public void initNewServiceOrder() {
		this.newServiceOrder = new ServiceOrder();
		orderTypes = ServiceOrderType.values();
	}

	private String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}
	
	public void findClientByName(String nameFilter){
		listServiceOrder = null;
		if(filterClient == null){
			listServiceOrder = serviceOrderService.findAll();
		}
		listServiceOrder = serviceOrderService.findClientByName(nameFilter);
	}
	
	public void findBySo(Long number){
		listServiceOrder = new ArrayList<ServiceOrder>();
		if (filterSo == null || filterSo.equals(0l)){
			listServiceOrder = serviceOrderService.findAll();
		}
		ServiceOrder os = serviceOrderService.find(number);
		if(os != null){
			listServiceOrder.add(os);
		}
	}
	
	public String getFilterClient() {
		return filterClient;
	}

	public void setFilterClient(String filterClient) {
		this.filterClient = filterClient;
	}

	public Long getFilterSo() {
		return filterSo;
	}

	public void setFilterSo(Long filterSo) {
		this.filterSo = filterSo;
	}

	public List<ServiceOrder> getListServiceOrder() {
		return listServiceOrder;
	}

	public void setListServiceOrder(List<ServiceOrder> listServiceOrder) {
		this.listServiceOrder = listServiceOrder;
	}
	
	public ServiceOrderType[] getOrderTypes(){
		return orderTypes;
	}
	
}
