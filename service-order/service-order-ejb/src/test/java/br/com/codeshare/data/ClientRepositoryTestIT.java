package br.com.codeshare.data;

import br.com.codeshare.model.Client;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by mcqueide on 31/12/16.
 */
public class ClientRepositoryTestIT {

    private ClientRepository repository;
    private EntityTransaction transaction;

    @Test
    public void findClientByNameTest(){
        repository = new ClientRepository();
        repository.em = Persistence.createEntityManagerFactory("primary").createEntityManager();
        transaction = repository.em.getTransaction();

        transaction.begin();

        Client client = new Client();
        client.setName("John");
        client.setAddress("Quadra 603 Conjunto 02 Casa 14");
        client.setHomePhone("(61)99874-5698");

        repository.em.persist(client);

        repository.em.flush();

        List<Client> clients = repository.findClientByName("John");

        Assert.assertEquals("john",clients.get(0).getName());
    }

}
