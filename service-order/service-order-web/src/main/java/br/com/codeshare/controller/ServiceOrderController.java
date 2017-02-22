package br.com.codeshare.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.qualifiers.SessionMap;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.service.ServiceOrderService;
import br.com.codeshare.util.WebResources;

@Named
@ViewScoped
public class ServiceOrderController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	@Inject @SessionMap
	private Map<String, Object> sessionMap;
	@Inject
	private ServiceOrderService serviceOrderService;
	@Inject
	private PhoneService phoneService;
	
//	@Inject
//	private Conversation conversation;

	private ServiceOrder newServiceOrder;
	private String filterClient;
	private Long filterSo;
	private List<ServiceOrder> listServiceOrder;
	private ServiceOrderType[] orderTypes;
	private ServiceOrderState[] orderStates;
	private List<Phone> phones;
	private ServiceOrder soSelected;

	@Produces
	@Named
	public ServiceOrder getNewServiceOrder() {
		return newServiceOrder;
	}
	
	public void save() throws Exception {
		try {
			newServiceOrder.setDateSo(LocalDate.now());
			serviceOrderService.register(newServiceOrder);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebResources.getMessage("register"), WebResources.getMessage("sucess_register")));
			initNewServiceOrder();
			
			/*if(!conversation.isTransient()){
				conversation.end();
			}*/
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
		}
	}
	
	public String update(ServiceOrder so) throws Exception {
		try {
			serviceOrderService.update(so);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebResources.getMessage("register"), WebResources.getMessage("sucess_register")));
			initNewServiceOrder();
			
			/*if(!conversation.isTransient()){
				conversation.end();
			}*/
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
		}
		
		return "service-order";
	}

	@PostConstruct
	public void initNewServiceOrder() {
		this.newServiceOrder = new ServiceOrder();
		orderTypes = ServiceOrderType.values();
		orderStates = ServiceOrderState.values();
		listServiceOrder = serviceOrderService.findAll();
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

	public void findClientByName() {
		listServiceOrder = null;
		if (filterClient == null) {
			listServiceOrder = serviceOrderService.findAll();
		}
		listServiceOrder = serviceOrderService.findClientByName(filterClient);
	}

	public void findBySo() {
		listServiceOrder = new ArrayList<ServiceOrder>();
		if (filterSo == null || filterSo.equals(0l)) {
			listServiceOrder = serviceOrderService.findAll();
		}
		ServiceOrder os = serviceOrderService.find(filterSo);
		if (os != null) {
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

	public ServiceOrderType[] getOrderTypes() {
		return orderTypes;
	}
	
	public ServiceOrderState[] getOrderStates() {
		return orderStates;
	}

	public void onClientChange(){
		/*if(conversation.isTransient()){
			conversation.begin();
		}*/
		
		if(newServiceOrder !=null && !newServiceOrder.equals(""))
            phones = phoneService.findPhoneByClientId(newServiceOrder.getClient().getId());
        else
            phones = new ArrayList<Phone>();
	}
	
	public List<Phone> getPhones() {
		return phones;
	}
	
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public String detail(ServiceOrder so){
		this.soSelected = so;
		sessionMap.put("so", so);
		return "detail_so";
	}
	
	public String edit(ServiceOrder so){
		this.soSelected = so;
		sessionMap.put("so", so);
		return "update_so";
	}
	
	public ServiceOrder getSoSelected() {
		ServiceOrder so = (ServiceOrder)sessionMap.get("so");
		return so;
	}
	
	public void searchByName(){
		listServiceOrder = null;
		if(filterClient == null){
			listServiceOrder = serviceOrderService.findAll();
		}
		listServiceOrder = serviceOrderService.findClientByName(filterClient);
		
	}
	
	public void searchById(){
		listServiceOrder = new ArrayList<ServiceOrder>();
		if(filterSo == null || filterSo.equals(0l)){
			listServiceOrder = serviceOrderService.findAll();
			return;
		}
		ServiceOrder os = serviceOrderService.find(filterSo);
		if (os != null){
			listServiceOrder.add(os);
		}
	}
	
	public ServiceOrder soSelected(){
		return soSelected;
	}
}
