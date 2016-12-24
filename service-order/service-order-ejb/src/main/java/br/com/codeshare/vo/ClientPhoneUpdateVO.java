package br.com.codeshare.vo;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

import java.util.List;

/**
 * Created by mcqueide on 24/12/16.
 */
public class ClientPhoneUpdateVO {

    private Client client;
    private List<Phone> phonesToBeRemoved;

    public ClientPhoneUpdateVO() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Phone> getPhonesToBeRemoved() {
        return phonesToBeRemoved;
    }

    public void setPhonesToBeRemoved(List<Phone> phonesToBeRemoved) {
        this.phonesToBeRemoved = phonesToBeRemoved;
    }
}
