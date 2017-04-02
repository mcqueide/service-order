package br.com.codeshare.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.service.ClientService;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;

@Path("/client")
@RequestScoped
public class ClientResourceRESTService {

	@Inject
	private Logger log; 
	
	@Inject
	private ClientService service;
	
	@Inject
	private PhoneService phoneService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientVO> listAllClients(){
		
		List<ClientVO> clients = service.findAll();
		
		
		if(clients == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return clients;
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientVO retrieveClientById(@PathParam("id") Long id){
		ClientVO client = service.findById(id);
		
		if(client == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return client;
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}/phones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PhoneVO> listClientPhones(@PathParam("id")Long id){
		List<PhoneVO> phones = phoneService.findPhoneByClientId(id);
		
		if(phones == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return phones;
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(ClientVO client) {

		ResponseBuilder builder;

		try {
			service.save(client);

			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		}catch (BusinessException be) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", be.getErrorCode());
			be.printStackTrace();
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		} catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
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
	public Response updateClient(ClientVO clientVO){
		
		ResponseBuilder builder;
		
		try{
			service.update(clientVO);
			
			builder = Response.ok();
		}catch (ConstraintViolationException ce) {
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		
		return builder.build();
	}

	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
}
