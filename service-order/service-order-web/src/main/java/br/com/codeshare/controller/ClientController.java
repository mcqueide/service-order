package br.com.codeshare.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.service.ClientService;

@Named
@ViewScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClientService clientService;

	private Client newClient;

	@Inject
	private PhoneController phoneController;

	@Produces
	@Named
	public Client getNewClient() {
		return newClient;
	}

	public String save() throws Exception {
		try {
			clientService.save(newClient);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!","Registration successful"));
			initNewClient();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	@PostConstruct
	public void initNewClient() {
		newClient = new Client();
		newClient.setTelefones(new ArrayList<Phone>());
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
		if (newClient.getTelefones() == null) {
			newClient.setTelefones(new ArrayList<Phone>());
		}
		newClient.getTelefones().add(phoneController.getNewPhone());

	}
}
