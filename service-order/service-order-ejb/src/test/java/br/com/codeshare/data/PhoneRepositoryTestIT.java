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
 * Created by mcqueide on 14/01/17.
 */
public class PhoneRepositoryTestIT {

    private PhoneRepository repository;
    private ClientRepository clientRepository;
    private EntityTransaction transaction;
    private Conversor conversor;

    @Before
    public void init(){
        repository = new PhoneRepository();
        clientRepository = new ClientRepository();
        EntityManager entityManager = Persistence.createEntityManagerFactory("primary").createEntityManager();
        repository.em = entityManager;
        clientRepository.em = entityManager;
        transaction = entityManager.getTransaction();
        Logger logger = Mockito.mock(Logger.class);
        repository.log = logger;
        clientRepository.log = logger;

        transaction.begin();
        conversor = new Conversor(new Resources().produceMapper());
    }

    @After
    public void destroy(){
        transaction.rollback();
        repository.em.close();
    }

    @Test
    public void findByClientIdTest(){
        PhoneVO motorola = new PhoneBuilder().withBrand("Motorola").withModel("Moto G4")
                .withEsn("123456789").buid();

        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").withPhone(Arrays.asList(motorola))
                .build();

        Phone motorolaPersist = conversor.converter(motorola, Phone.class);
        Client clientPersist = conversor.converter(client, Client.class);
        motorolaPersist.setClient(clientPersist);

        clientRepository.insert(clientPersist);
        repository.insert(motorolaPersist);

        List<Phone> phones = repository.findByClientId(clientPersist.getId());

        Assert.assertEquals(phones.size(),1);
        Assert.assertEquals(phones.get(0).getBrand(),"Motorola");

    }

}
