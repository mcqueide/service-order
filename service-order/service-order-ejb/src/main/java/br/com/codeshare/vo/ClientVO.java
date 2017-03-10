package br.com.codeshare.vo;

import java.util.List;

/**
 * Created by mcqueide on 28/02/17.
 */
public class ClientVO {

    private Long id;
    private String name;
    private String address;
    private String homePhone;
    private String businessPhone;
    private List<PhoneVO> phones;
    private List<PhoneVO> phonesToBeRemoved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public List<PhoneVO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneVO> phones) {
        this.phones = phones;
    }

    public List<PhoneVO> getPhonesToBeRemoved() {
        return phonesToBeRemoved;
    }

    public void setPhonesToBeRemoved(List<PhoneVO> phonesToBeRemoved) {
        this.phonesToBeRemoved = phonesToBeRemoved;
    }
}
