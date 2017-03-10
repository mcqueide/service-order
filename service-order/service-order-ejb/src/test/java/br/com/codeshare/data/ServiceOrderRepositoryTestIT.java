package br.com.codeshare.data;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.builder.ServiceOrderBuild;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.util.Conversor;
import br.com.codeshare.util.Resources;
import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;
import br.com.codeshare.vo.ServiceOrderVO;
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
    private Conversor conversor;

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
        conversor = new Conversor(new Resources().produceMapper());
    }

    @Test
    public void saveServiceOrder(){
        PhoneVO motorola = new PhoneBuilder().withBrand("Motorola").withModel("Moto G4")
                .withEsn("123456789").buid();

        ClientVO client = new ClientBuilder().withName("John")
                .withAdress("Quadra 603 Conjunto 02 Casa 14")
                .withHomePhone("(61)99874-5698").withPhone(Arrays.asList(motorola))
                .build();

        ServiceOrderVO serviceOrder = new ServiceOrderBuild().withClient(client).withPhone(motorola)
                .withServiceOrderType(ServiceOrderType.SERVICE)
                .withReportedProblem("Doesn't work")
                .withDate(LocalDate.now())
                .withValue(new BigDecimal(50)).build();

        Phone motorolaPersist = conversor.converter(motorola, Phone.class);
        Client clientPersist = conversor.converter(client, Client.class);
        ServiceOrder serviceOrderPersist = conversor.converter(serviceOrder, ServiceOrder.class);

        motorolaPersist.setClient(clientPersist);

        serviceOrderPersist.setClient(clientPersist);
        serviceOrderPersist.setPhone(motorolaPersist);

        repository.insert(clientPersist);
        phoneRepository.insert(motorolaPersist);
        soRepository.insert(serviceOrderPersist);

        Assert.assertNotNull(clientPersist.getId());
        Assert.assertNotNull(motorolaPersist.getId());
        Assert.assertNotNull(serviceOrderPersist.getId());

        ServiceOrder soById = soRepository.findById(serviceOrderPersist.getId());

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
