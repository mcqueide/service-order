package br.com.codeshare.data;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.util.Resources;
import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mcqueide on 31/12/16.
 */
public class ClientRepositoryTestIT {

    private ClientRepository repository;
    private PhoneRepository phoneRepository;
    private EntityTransaction transaction;
    private Conversor conversor;

    @Before
    public void init(){
        repository = new ClientRepository();
        phoneRepository = new PhoneRepository();
        EntityManager entityManager =Persistence.createEntityManagerFactory("primary").createEntityManager();
        repository.em = entityManager;
        phoneRepository.em = entityManager;
        transaction = repository.em.getTransaction();
        Logger logger = Mockito.mock(Logger.class);
        repository.log = logger;
        phoneRepository.log = logger;

        transaction.begin();

        conversor = new Conversor(new Resources().produceMapper());
    }

    @After
    public void destroy(){
        transaction.rollback();
        repository.em.close();
    }

    @Test
    public void findClientByNameTest(){
        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        Client persist = conversor.converter(client, Client.class);
        repository.insert(persist);

        List<Client> clients = repository.findClientByName("John");

        Assert.assertEquals(clients.size(),1);
        Assert.assertEquals("John",clients.get(0).getName());
    }

    @Test
    public void saveClientWithPhoneNumberTest(){
        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        Client persist = conversor.converter(client, Client.class);
        repository.insert(persist);

        Assert.assertNotNull(persist.getId());
    }

    @Test
    public void saveClientWithPhoneNumberAndPhoneTest(){
        PhoneVO motorola = new PhoneBuilder().withBrand("Motorola").withModel("Moto G4")
                .withEsn("123456789").buid();

        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").withPhone(Arrays.asList(motorola))
                .build();

        Phone motorolaPersist = conversor.converter(motorola, Phone.class);
        Client clientPersist = conversor.converter(client, Client.class);
        motorolaPersist.setClient(clientPersist);

        repository.insert(clientPersist);
        phoneRepository.insert(motorolaPersist);

        Assert.assertNotNull(clientPersist.getId());
        Assert.assertNotNull(motorolaPersist.getId());

        Client clientById = repository.findClientById(clientPersist.getId());

        Assert.assertEquals(clientById.getName(),"John");
        Assert.assertEquals(clientById.getPhones().get(0).getBrand(),"Motorola");

    }

    @Test
    public void updateClientTest(){
        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        Client clientPersist = conversor.converter(client, Client.class);
        repository.insert(clientPersist);

        Client clientRecovered = repository.findClientById(clientPersist.getId());
        clientRecovered.setBusinessPhone("(61)3333-1987");
        repository.update(clientRecovered);

        Client clientForTest = repository.findClientById(clientPersist.getId());
        Assert.assertEquals(clientForTest.getBusinessPhone(),"(61)3333-1987");
    }

    @Test
    public void findClientByOrderedNameTest(){
        ClientVO peter = new ClientBuilder().withName("Peter")
                .withAdress("Quadra 405 Conjunto 02 Casa 14")
                .withHomePhone("(61)96574-5698").build();

        ClientVO john = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        ClientVO jon = new ClientBuilder().withName("Jon")
                .withAdress("Quadra 803 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").build();

        repository.insert(conversor.converter(peter,Client.class));
        repository.insert(conversor.converter(john,Client.class));
        repository.insert(conversor.converter(jon,Client.class));

        List<Client> clients = repository.findAllOrderedByName();

        Assert.assertEquals(clients.get(0).getName(),"John");
        Assert.assertEquals(clients.get(1).getName(),"Jon");
        Assert.assertEquals(clients.get(2).getName(),"Peter");
    }

}
