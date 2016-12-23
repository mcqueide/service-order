package br.com.codeshare.data;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

@RunWith(PowerMockRunner.class)
public class ClientRepositoryTest {
	
    private EntityManager em;
    private EntityManagerFactory emf;

	@InjectMocks
	private ClientRepository repository;

	@Before
	public void begin(){
        emf = Persistence.createEntityManagerFactory("primary");
        em = emf.createEntityManager();
        Mockito.when(repository.getEntityManager()).thenReturn(em);

        em.getTransaction().begin();
	}
	
	@After
    public void end(){
	    em.close();
	    emf.close();
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

		repository.insert(client);
		repository.getEntityManager().getTransaction().commit();

		Client c = repository.findById(client.getId());

		Assert.assertEquals(client.getName(), c.getName());

		repository.getEntityManager().close();
	}


	
}
