package br.com.codeshare.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.State;
import br.com.codeshare.service.ClientService;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.service.StateService;
import br.com.codeshare.util.WebResources;

@Named
@ConversationScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	@Inject
	private ExternalContext externalContext;
	@Inject
	private ClientService clientService;
	@Inject
	private StateService stateService;

	private Client newClient;

	@Inject
	private PhoneController phoneController;
	@Inject
	private PhoneService phoneService;
	
	@Inject
	private Conversation conversation;
	
	private String filterName;

	private List<Client> listClients;
	
	private List<State> states;
	
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
		newClient.setPhones(new ArrayList<Phone>());
		if(externalContext.getRequestServletPath().equals("/clients.jsf")){
			listClients = clientService.findAll();
		}
		states = new ArrayList<>();
		states = stateService.findAll();
	}
	
	public String save() throws Exception {
		try {
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

	public void addClientPhone(Client client) {
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		phoneController.getNewPhone().setClient(client);
		if (client.getPhones() == null) {
			client.setPhones(new ArrayList<Phone>());
		}
		client.getPhones().add(phoneController.getNewPhone());
		phoneController.initNewPhone();
	}
	
	public void initConversation(){
		if(conversation.isTransient()){
			conversation.begin();
		}
	}
	
	public void removeClientPhone(Client client, Phone phone){
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		client.getPhones().remove(phone);
		if(phoneToBeRemove == null){
			phoneToBeRemove = new ArrayList<Phone>();
		}
		phoneToBeRemove.add(phone);
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
		clientSelected.setPhones(phoneList);
		return "update_client";
	}
	
	public Client getClientSelected() {
		return clientSelected;
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
		
}