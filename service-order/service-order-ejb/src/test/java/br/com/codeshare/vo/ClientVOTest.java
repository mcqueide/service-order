package br.com.codeshare.vo;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ClientVOTest {
	
	@Test
	public void converteClientEntityToClientVOTest(){

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

        ClientVO clientVO = new ClientVO(client);

        assertEquals(client.getName(),clientVO.getName());
        assertEquals(client.getAddress(),clientVO.getAddress());
        assertEquals(client.getHomePhone(),clientVO.getHomePhone());
        assertEquals(client.getPhones().get(0).getBrand(),clientVO.getPhonesVO().get(0).getBrand());
        assertEquals(client.getPhones().get(0).getModel(),clientVO.getPhonesVO().get(0).getModel());
    }

    @Test
    public void converteClientVOToClientEntityTest(){
        PhoneVO phoneVO = new PhoneVO();
        phoneVO.setBrand("Motorola");
        phoneVO.setModel("Moto G 4");

        ClientVO clientVO = new ClientVO();
        clientVO.setName("John Mc.Queide");
        clientVO.setAddress("Quadra 804 Conjunto 13 Casa 05");
        clientVO.setHomePhone("(61) 3333-9999");
        clientVO.setPhonesVO(Arrays.asList(phoneVO));


        Client client = clientVO.fromDTO();

        assertEquals(clientVO.getName(),client.getName());
        assertEquals(clientVO.getAddress(),client.getAddress());
        assertEquals(clientVO.getHomePhone(),client.getHomePhone());
        assertEquals(clientVO.getPhonesVO().get(0).getBrand(),client.getPhones().get(0).getBrand());
        assertEquals(clientVO.getPhonesVO().get(0).getModel(),client.getPhones().get(0).getModel());
    }

}
