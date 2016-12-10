package br.com.codeshare.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.builder.ServiceOrderBuild;
import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.util.PackageUtil;

@RunWith(Arquillian.class)
@Ignore
public class ServiceOrderServiceTest {

	@Deployment
	public static Archive<?> createTestArchive(){
		return ShrinkWrap.create(WebArchive.class,"test.war")
				.addPackage(PackageUtil.MODEL.getPackageName())
				.addPackage(PackageUtil.SERVICE.getPackageName())
				.addPackage(PackageUtil.DATA.getPackageName())
				.addPackage(PackageUtil.UTIL.getPackageName())
				.addPackage(PackageUtil.ENUMS.getPackageName())
				.addPackage(PackageUtil.BUILDER.getPackageName())
				.addPackage(PackageUtil.EXCEPTION.getPackageName())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}
	
	@Inject
	ServiceOrderService service;
	
	@Inject
	ClientService clientService;
	
	@Inject
	PhoneService phoneService;
	
	@Inject
	Logger log;
	
	@Test
	public void testRegister(){
		
		Phone phone = getPhone();
		Client client = getClient(phone);
		ServiceOrder so = getSO(client, phone);
		
		try {
			clientService.save(client);
			service.register(so);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertNotNull(so.getId());
		log.info(so.getClient().getName() + "\'s service order was persisted with id " + so.getId());
	}
	
	@Test
	public void testUpdate(){
		Phone phone = getPhone();
		Client client = getClient(phone);
		ServiceOrder so = getSO(client, phone);
		
		try{
			clientService.save(client);
			service.register(so);
			
			so.setValue(new BigDecimal(1000));
			so.setSoState(ServiceOrderState.APPROVED);
			
			service.update(so);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ServiceOrder soUpdated = service.find(so.getId());
		
		Assert.assertEquals(new BigDecimal("1000.00"), soUpdated.getValue());
		Assert.assertEquals(ServiceOrderState.APPROVED, soUpdated.getSoState());
		log.info(so.getClient().getName() + "\'s service order with id " + so.getId() + " was updated");
	}
	
	private Phone getPhone(){
		Phone phone = new PhoneBuilder()
				.withBrand("Samsung")
				.withModel("Galaxy S6")
				.buid();
		
		return phone;
	}
	
	private Client getClient(Phone...phones){
		Client client = new ClientBuilder()
				.withName("John Mc.Queide")
				.withAdress("Quadra 101 Conjunto 07 Casa 07")
				.withHomePhone("(61)1234-9812")
				.withBusinessPhone("")
				.withPhone(Arrays.asList(phones))
				.build();
		return client;
	}
	
	@SuppressWarnings("unused")
	private Client getClient(){
		Client client = new ClientBuilder()
				.withName("John Mc.Queide")
				.withAdress("Quadra 101 Conjunto 07 Casa 07")
				.withHomePhone("(61)1234-9812")
				.withBusinessPhone("")
				.build();
		return client;
	}
	
	private ServiceOrder getSO(Client client,Phone phone){
		ServiceOrder serviceOrder = new ServiceOrderBuild()
				.withReportedProblem("Don't work")
				.withValue(new BigDecimal(500))
				.withClient(client)
				.withPhone(phone)
				.build();
		
		return serviceOrder;
	}
	
}
