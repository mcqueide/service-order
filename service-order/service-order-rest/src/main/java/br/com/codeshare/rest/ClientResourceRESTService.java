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
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.enums.ErrorCode;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;

@Path("/client")
@RequestScoped
public class ClientResourceRESTService {

	@Inject
	private Logger log; 
	
	@Inject
	private Validator validator;
	
	@Inject
	private ClientRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> listAllClients(){
		
		List<Client> clients = repository.findAllOrderedByName();
		
		
		if(clients == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		for (Client client : clients) {
			client.setOrdemServicos(null);
			client.setTelefones(null);
		}
		
		return clients; 
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client lookupClientById(@PathParam("id") Long id){
		Client client = repository.findById(id);
		
		if(client == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		client.setOrdemServicos(null);
		client.setTelefones(null);
		return client;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(Client client){
		
		Response.ResponseBuilder builder = null;
		
		try{
			validateClient(client);
			
			repository.insert(client);
			
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
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
		
		try {
			validatePhoneLeastOnePhoneObligatory(client);
		} catch (BusinessException e) {
			throw new ValidationException(e.getErrorCode(), e);
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
	
	private void validatePhoneLeastOnePhoneObligatory(Client client) throws BusinessException {
		if(client.getHomePhone().isEmpty() && client.getBisenessPhone().isEmpty()){
			throw new BusinessException(ErrorCode.LEAST_ONE_PHONE_OBLIGATORY.getErrorCode());
		}
	}
	
}
