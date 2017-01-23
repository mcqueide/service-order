package br.com.codeshare.data;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mcqueide on 14/01/17.
 */
public class PhoneRepositoryTestIT {

    private PhoneRepository repository;
    private ClientRepository clientRepository;
    private EntityTransaction transaction;

    @Before
    public void init(){
        repository = new PhoneRepository();
        clientRepository = new ClientRepository();
        repository.em = Persistence.createEntityManagerFactory("primary").createEntityManager();
        clientRepository.em = Persistence.createEntityManagerFactory("primary").createEntityManager();
        transaction = clientRepository.em.getTransaction();
        Logger logger = Mockito.mock(Logger.class);
        repository.log = logger;
        clientRepository.log = logger;

        transaction.begin();
    }

    @After
    public void destroy(){
        transaction.rollback();
        repository.em.close();
    }

    @Test
    public void findByClientIdTest(){
        Phone motorola = new PhoneBuilder().withBrand("Motorola").withModel("Moto G4")
                .withEsn("123456789").buid();

        Client client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").withPhone(Arrays.asList(motorola))
                .build();

        motorola.setClient(client);

        clientRepository.insert(client);
        repository.insert(motorola);

        List<Phone> phones = repository.findByClientId(client.getId());

        Assert.assertEquals(phones.size(),1);
        Assert.assertEquals(phones.get(0).getBrand(),"Motorola");

    }

}
