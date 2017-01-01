package br.com.codeshare.data;

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
        Client client = new Client();
        client.setName("John");
        client.setAddress("Quadra 603 Conjunto 02 Casa 14");
        client.setHomePhone("(61)99874-5698");
        client.setBusinessPhone("(61)99874-5698");

        repository.em.persist(client);

        List<Client> clients = repository.findClientByName("John");

        Assert.assertEquals(clients.size(),1);
        Assert.assertEquals("John",clients.get(0).getName());
    }

}
