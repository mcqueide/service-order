package br.com.codeshare.data;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.model.Client;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mcqueide on 31/12/16.
 */
public class ClientRepositoryTestIT {

    private ClientRepository repository;
    private EntityTransaction transaction;

    @Before
    public void init(){
        repository = new ClientRepository();
        repository.em = Persistence.createEntityManagerFactory("primary").createEntityManager();
        transaction = repository.em.getTransaction();
        Logger logger = Mockito.mock(Logger.class);
        repository.log = logger;

        transaction.begin();
    }

    @After
    public void destroy(){
        transaction.rollback();
        repository.em.close();
    }

    @Test
    public void findClientByNameTest(){
        Client client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        repository.insert(client);

        List<Client> clients = repository.findClientByName("John");

        Assert.assertEquals(clients.size(),1);
        Assert.assertEquals("John",clients.get(0).getName());
    }

    @Test
    public void saveClientWithPhoneNumberTest(){
        Client client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        repository.insert(client);

        Assert.assertNotNull(client.getId());
    }

    @Test
    public void updateClientTest(){
        Client client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        repository.insert(client);

        Client clientRecovered = repository.findClientById(client.getId());
        clientRecovered.setBusinessPhone("(61)3333-1987");
        repository.update(clientRecovered);

        Client clientForTest = repository.findClientById(client.getId());
        Assert.assertEquals(clientForTest.getBusinessPhone(),"(61)3333-1987");
    }

    @Test
    public void findClientByOrderedNameTest(){
        Client peter = new ClientBuilder().withName("Peter")
                .withAdress("Quadra 405 Conjunto 02 Casa 14")
                .withHomePhone("(61)96574-5698").build();

        Client john = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        Client jon = new ClientBuilder().withName("Jon")
                .withAdress("Quadra 803 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        repository.insert(peter);
        repository.insert(john);
        repository.insert(jon);

        List<Client> clients = repository.findAllOrderedByName();

        Assert.assertEquals(clients.get(0).getName(),"John");
        Assert.assertEquals(clients.get(1).getName(),"Jon");
        Assert.assertEquals(clients.get(2).getName(),"Peter");
    }

}
