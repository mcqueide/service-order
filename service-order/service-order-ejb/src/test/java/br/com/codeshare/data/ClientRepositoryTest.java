package br.com.codeshare.data;

import java.util.Arrays;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

@RunWith(PowerMockRunner.class)
@Ignore
public class ClientRepositoryTest {
	
	@Mock
	private ClientRepository repository;
	
	@Before
	public void begin(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mockPU");
		repository.em = emf.createEntityManager();
	}
	
	@Test
	public void ClientSaveTest(){
		
		Phone phone = new PhoneBuilder()
				.withBrand("Samsung")
				.withModel("Galaxy S6")
				.buid();
		
		Client client = new ClientBuilder()
				.withName("John Mc.Queide")
				.withAdress("Quadra 101 Conjunto 07 Casa 07")
				.withHomePhone("(61) 1234-9812")
				.withPhone(Arrays.asList(phone))
				.build();
		
		repository.em.getTransaction().begin();
		repository.insert(client);
		repository.em.getTransaction().commit();

		Client c = repository.findById(client.getId());
		
		Assert.assertEquals(client.getName(), c.getName());
		
		repository.em.close();
	}
	
}
