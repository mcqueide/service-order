package br.com.codeshare.controller;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.qualifiers.SessionMap;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.service.PhoneStateService;
import br.com.codeshare.service.ServiceOrderService;
import br.com.codeshare.util.WebResources;
import br.com.codeshare.vo.PhoneStateVO;
import br.com.codeshare.vo.PhoneVO;
import br.com.codeshare.vo.ServiceOrderPhoneVO;
import br.com.codeshare.vo.ServiceOrderVO;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ConversationScoped
public class ServiceOrderController implements Serializable{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("CdiUnproxyableBeanTypesInspection")
    @Inject
	private FacesContext facesContext;
    @SuppressWarnings("CdiUnproxyableBeanTypesInspection")
	@Inject
	private ExternalContext externalContext;
	@Inject
	private ServiceOrderService serviceOrderService;
	@Inject
	private PhoneService phoneService;
	@Inject
	private PhoneStateService phoneStateService;
	@Inject
	private Conversation conversation;
	private ServiceOrderVO newServiceOrder;
	private String filterClient;
	private Long filterSo;
	private List<ServiceOrderVO> listServiceOrder;
	private ServiceOrderType[] orderTypes;
	private ServiceOrderState[] orderStates;
	private List<PhoneVO> phones;
	private ServiceOrderVO soSelected;
	private ServiceOrderPhoneVO serviceOrderPhoneVO;
	private List<PhoneStateVO> phoneStates;
	private List<PhoneStateVO> phoneStatesSelected;
	private PhoneVO phoneSelected;

	@Produces
	@Named
	public ServiceOrderVO getNewServiceOrder() {
		return newServiceOrder;
	}

    @PostConstruct
    public void initNewServiceOrder() {
        this.newServiceOrder = new ServiceOrderVO();
        this.newServiceOrder.setSoPhonePhoneState(new ArrayList<>());
        orderTypes = ServiceOrderType.values();
        orderStates = ServiceOrderState.values();
		if(externalContext.getRequestServletPath().equals("/so/service-order.jsf")){
        	listServiceOrder = serviceOrderService.findAll();
		}
        phoneStates = phoneStateService.findAll();
        phoneStatesSelected = new ArrayList<>();
        phoneSelected = new PhoneVO();
    }
	
	public void save() throws Exception {
		try {
			newServiceOrder.setDateSo(LocalDate.now());
			phoneStatesSelected.forEach(phoneState -> newServiceOrder.getSoPhonePhoneState().add(new ServiceOrderPhoneVO(phoneState,phoneSelected)));
			serviceOrderService.register(newServiceOrder);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebResources.getMessage("register"), WebResources.getMessage("sucess_register")));
			initNewServiceOrder();

			if(!conversation.isTransient()){
				conversation.end();
			}
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
		}
	}
	
	public String update(ServiceOrderVO so) throws Exception {
		try {
			serviceOrderService.update(so);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebResources.getMessage("register"), WebResources.getMessage("sucess_register")));
			initNewServiceOrder();
			
			if(!conversation.isTransient()){
				conversation.end();
			}
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
		}
		
		return "service-order";
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

	public List<ServiceOrderVO> getListServiceOrder() {
		return listServiceOrder;
	}

	public ServiceOrderType[] getOrderTypes() {
		return orderTypes;
	}
	
	public ServiceOrderState[] getOrderStates() {
		return orderStates;
	}

	public void onClientChange(){
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		if(newServiceOrder !=null)
            phones = phoneService.findPhoneByClientId(newServiceOrder.getClient().getId());
        else
            phones = new ArrayList<>();
	}
	
	public List<PhoneVO> getPhones() {
		return phones;
	}
	
	public void setPhones(List<PhoneVO> phones) {
		this.phones = phones;
	}
	
	public String detail(ServiceOrderVO so){
		if(conversation.isTransient()){
			conversation.begin();
		}
		this.soSelected = so;
		return "detail_so";
	}
	
	public String edit(ServiceOrderVO so){
		if(conversation.isTransient()){
			conversation.begin();
		}
		this.soSelected = so;
		return "update_so";
	}
	
	public ServiceOrderVO getSoSelected() {
		return this.soSelected;
	}
	
	public void searchByName(){
		listServiceOrder = null;
		if(filterClient == null){
			listServiceOrder = serviceOrderService.findAll();
		}
		listServiceOrder = serviceOrderService.findClientByName(filterClient);
		
	}
	
	public void searchById(){
		listServiceOrder = new ArrayList<>();
		if(filterSo == null || filterSo.equals(0L)){
			listServiceOrder = serviceOrderService.findAll();
			return;
		}
		ServiceOrderVO os = serviceOrderService.find(filterSo);
		if (os != null){
			listServiceOrder.add(os);
		}
	}
	
	public ServiceOrderVO soSelected(){
		return soSelected;
	}

	public ServiceOrderPhoneVO getServiceOrderPhoneVO() {
		return serviceOrderPhoneVO;
	}

	public void setServiceOrderPhoneVO(ServiceOrderPhoneVO serviceOrderPhoneVO) {
		this.serviceOrderPhoneVO = serviceOrderPhoneVO;
	}

	public List<PhoneStateVO> getPhoneStates() {
		return phoneStates;
	}

	public void setPhoneStates(List<PhoneStateVO> phoneStates) {
		this.phoneStates = phoneStates;
	}

	public List<PhoneStateVO> getPhoneStatesSelected() {
		return phoneStatesSelected;
	}

	public void setPhoneStatesSelected(List<PhoneStateVO> phoneStatesSelected) {
		this.phoneStatesSelected = phoneStatesSelected;
	}

	public PhoneVO getPhoneSelected() {
		return phoneSelected;
	}

	public void setPhoneSelected(PhoneVO phoneSelected) {
		this.phoneSelected = phoneSelected;
	}
}
