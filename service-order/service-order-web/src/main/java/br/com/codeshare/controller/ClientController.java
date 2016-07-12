package br.com.codeshare.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.enums.ErrorCode;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.qualifiers.SessionMap;
import br.com.codeshare.service.ClientService;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.util.WebResources;

@Named
@ConversationScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	@Inject @SessionMap
	private Map<String, Object> sessionMap;
	@Inject
	private ClientService clientService;

	private Client newClient;

	@Inject
	private PhoneController phoneController;
	@Inject
	private PhoneService phoneService;
	
	@Inject
	private Conversation conversation;
	
	private String filterName;

	private List<Client> listClients;

	private Client clientSelected;
	private List<Phone> phoneToBeRemove;
	
	@Produces
	@Named
	public Client getNewClient() {
		return newClient;
	}

	@PostConstruct
	public void initNewClient() {
		newClient = new Client();
		newClient.setTelefones(new ArrayList<Phone>());
		listClients = clientService.findAll();
	}
	
	public String save() throws Exception {
		try {
			validatePhoneLeastOnePhoneObligatory(newClient);
			
			clientService.save(newClient);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebResources.getMessage("register"),WebResources.getMessage("sucess_register")));
			initNewClient();
		}catch (BusinessException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,WebResources.getMessage(e.getErrorCode()),"");
			facesContext.addMessage(null, m);
		}catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
		}
		if(!conversation.isTransient()){
			conversation.end();
		}
		return null;
	}

	public String update(Client client) throws Exception{
		try {
			validatePhoneLeastOnePhoneObligatory(client);
			
			clientService.update(client,phoneToBeRemove);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  WebResources.getMessage("register"),WebResources.getMessage("sucess_register")));
			initNewClient();
		}catch (BusinessException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,WebResources.getMessage(e.getErrorCode()),"");
			facesContext.addMessage(null, m);
			return null;
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, WebResources.getMessage("unsuccessful"));
			facesContext.addMessage(null, m);
			return null;
		}
		if(!conversation.isTransient()){
			conversation.end();
		}
		return "clients";
	}
	
	private void validatePhoneLeastOnePhoneObligatory(Client client) throws BusinessException {
		if(client.getHomePhone().isEmpty() && client.getBisenessPhone().isEmpty()){
			throw new BusinessException(ErrorCode.LEAST_ONE_PHONE_OBLIGATORY.getErrorCode());
		}
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

	public void addClientPhone() {
		
		phoneController.getNewPhone().setClient(newClient);
		if (newClient.getPhones() == null) {
			newClient.setTelefones(new ArrayList<Phone>());
		}
		newClient.getPhones().add(phoneController.getNewPhone());
		phoneController.initNewPhone();
	}
	
	public void initConversation(){
		if(conversation.isTransient()){
			conversation.begin();
		}
	}
	
	public void removeClientPhone(Phone phone){
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		clientSelected.getPhones().remove(phone);
		if(phoneToBeRemove == null){
			phoneToBeRemove = new ArrayList<Phone>();
		}
		phoneToBeRemove.add(phone);
	}
	
	public void addClientPhoneOnUpdate() {
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		phoneController.getNewPhone().setClient(clientSelected);
		if (clientSelected.getPhones() == null) {
			clientSelected.setTelefones(new ArrayList<Phone>());
		}
		clientSelected.getPhones().add(phoneController.getNewPhone());
		phoneController.initNewPhone();
	}
	
	public void searchByName() {
		listClients = null;
		if(filterName == null){
			listClients = clientService.findAll();
		}
		listClients = clientService.findByName(filterName);
	}
	
	public String edit(Client client) {
		if(conversation.isTransient()){
			conversation.begin();
		}
		this.clientSelected = client;
		List<Phone> phoneList = phoneService.findPhoneByClientId(clientSelected.getId());
		clientSelected.setTelefones(phoneList);
		sessionMap.put("client", client);
		return "update_client";
	}
	
	public Client getClientSelected() {
		return (Client) sessionMap.get("client");
	}
	
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<Client> getListClients() {
		return listClients;
	}
	
}
