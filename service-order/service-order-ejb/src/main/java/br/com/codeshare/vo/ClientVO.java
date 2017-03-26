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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientVO clientVO = (ClientVO) o;

        if (id != null ? !id.equals(clientVO.id) : clientVO.id != null) return false;
        if (name != null ? !name.equals(clientVO.name) : clientVO.name != null) return false;
        if (address != null ? !address.equals(clientVO.address) : clientVO.address != null) return false;
        if (homePhone != null ? !homePhone.equals(clientVO.homePhone) : clientVO.homePhone != null) return false;
        return businessPhone != null ? businessPhone.equals(clientVO.businessPhone) : clientVO.businessPhone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (businessPhone != null ? businessPhone.hashCode() : 0);
        return result;
    }
}
