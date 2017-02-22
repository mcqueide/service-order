package br.com.codeshare.data;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.builder.ServiceOrderBuild;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mcqueide on 22/02/17.
 */
public class ServiceOrderRepositoryTestIT {

    private ClientRepository repository;
    private PhoneRepository phoneRepository;
    private ServiceOrderRepository soRepository;
    private EntityTransaction transaction;

    @Before
    public void init(){
        repository = new ClientRepository();
        phoneRepository = new PhoneRepository();
        soRepository = new ServiceOrderRepository();
        EntityManager entityManager = Persistence.createEntityManagerFactory("primary").createEntityManager();
        repository.em = entityManager;
        phoneRepository.em = entityManager;
        soRepository.em = entityManager;
        transaction = entityManager.getTransaction();
        Logger logger = Mockito.mock(Logger.class);
        repository.log = logger;
        phoneRepository.log = logger;
        soRepository.log = logger;

        transaction.begin();
    }

    @Test
    public void saveServiceOrder(){
        Phone motorola = new PhoneBuilder().withBrand("Motorola").withModel("Moto G4")
                .withEsn("123456789").buid();

        Client client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").withPhone(Arrays.asList(motorola))
                .build();

        ServiceOrder serviceOrder = new ServiceOrderBuild().withClient(client).withPhone(motorola)
                .withServiceOrderType(ServiceOrderType.SERVICE)
                .withReportedProblem("Doesn't work")
                .withDate(LocalDate.now())
                .withValue(new BigDecimal(50)).build();

        motorola.setClient(client);

        repository.insert(client);
        phoneRepository.insert(motorola);
        soRepository.insert(serviceOrder);

        Assert.assertNotNull(client.getId());
        Assert.assertNotNull(motorola.getId());
        Assert.assertNotNull(serviceOrder.getId());

        ServiceOrder soById = soRepository.findById(serviceOrder.getId());
        List<ServiceOrder> allOrderedById = soRepository.findAllOrderedById();


        Assert.assertEquals(soById.getClient().getName(),"John");
        Assert.assertEquals(soById.getDateSo(),LocalDate.now());
        Assert.assertEquals(soById.getValue(),new BigDecimal(50));
        Assert.assertEquals(soById.getReportedProblem(),"Doesn't work");
        Assert.assertEquals(soById.getServiceOrderType(),ServiceOrderType.SERVICE);

    }

    @After
    public void destroy(){
        transaction.rollback();
        repository.em.close();
    }
}
