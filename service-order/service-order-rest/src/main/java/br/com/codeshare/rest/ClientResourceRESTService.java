package br.com.codeshare.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.service.ClientService;
import br.com.codeshare.vo.ClientPhoneUpdateVO;

@Path("/client")
@RequestScoped
public class ClientResourceRESTService {

	@Inject
	private Logger log; 
	
	@Inject
	private Validator validator;
	
	@Inject
	private ClientRepository repository;
	
	@Inject
	private ClientService service;
	
	@Inject
	private PhoneRepository phoneRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> listAllClients(){
		
		List<Client> clients = repository.findAllOrderedByName();
		
		
		if(clients == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		for (Client client : clients) {
			client.setServiceOrders(null);
			client.setPhones(null);
		}
		
		return clients; 
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client lookupClientById(@PathParam("id") Long id){
		Client client = repository.findClientById(id);
		
		if(client == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		client.setServiceOrders(null);

		for(Phone phone : client.getPhones()){
		    phone.setOs(null);
		    phone.setClient(null);
        }

		return client;
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}/phones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Phone> listClientPhones(@PathParam("id")Long id){
		List<Phone> phones = phoneRepository.findByClientId(id);
		
		if(phones == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		for (Phone phone : phones) {
			phone.setOs(null);
		}
		
		return phones;
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(Client client) {

		ResponseBuilder builder = null;

		try {
			if (client.getPhones() != null) {
				for (Phone phone : client.getPhones()) {
					phone.setClient(client);
				}
			}

			validateClient(client);

			service.save(client);

			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		}catch (BusinessException be) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", be.getErrorCode());
			be.printStackTrace();
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		} catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            e.printStackTrace();
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		
		return builder.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9][0-9]*}")
	public Response updateClient(ClientPhoneUpdateVO clientPhoneVO){
		
		ResponseBuilder builder = null;
		
		try{
			if (clientPhoneVO.getClient().getPhones() != null) {
				for (Phone phone : clientPhoneVO.getClient().getPhones()) {
					phone.setClient(clientPhoneVO.getClient());
				}
			}
			service.update(clientPhoneVO.getClient(), clientPhoneVO.getPhonesToBeRemoved());
			
			builder = Response.ok();
		}catch (ConstraintViolationException ce) {
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		
		return builder.build();
	}

	private void validateClient(Client client) {

		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		
		if(!violations.isEmpty()){
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
		
	}
	
	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
}
