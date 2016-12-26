package br.com.codeshare.vo;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String address;
	
	private String homePhone;
	
	private String businessPhone;
	
	private List<ServiceOrderVO> serviceOrdersVO;
	
	private List<PhoneVO> phonesVO;

	public ClientVO() {
	}

	public ClientVO(final Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.address = entity.getAddress();
		this.homePhone = entity.getHomePhone();
		this.businessPhone = entity.getBusinessPhone();

		if(entity.getServiceOrders() != null){
            List<ServiceOrderVO> osVO = new ArrayList<>();
            for (ServiceOrder os : entity.getServiceOrders()) {
                osVO.add(new ServiceOrderVO(os));
            }
            this.serviceOrdersVO = osVO;
        }

        if(entity.getPhones() != null){
            List<PhoneVO> phoneVO = new ArrayList<>();
            for (Phone phone : entity.getPhones()) {
                phoneVO.add(new PhoneVO(phone));
            }
            this.phonesVO = phoneVO;
        }
	}

	public Client fromDTO(){
        Client entity = new Client();

        entity.setId(this.id);
        entity.setName(this.name);
        entity.setAddress(this.address);
        entity.setHomePhone(this.homePhone);
        entity.setBusinessPhone(this.businessPhone);

        if(this.getServiceOrdersVO() != null){
            List<ServiceOrder> so = new ArrayList<>();
            for (ServiceOrderVO soVO : this.getServiceOrdersVO()) {
                so.add(soVO.fromDTO());
            }
            entity.setServiceOrders(so);
        }

        if(this.getPhonesVO() != null){
            List<Phone> phones = new ArrayList<>();
            for (PhoneVO phoneVO : this.getPhonesVO()) {
                phones.add(phoneVO.fromDTO());
            }
            entity.setPhones(phones);
        }

        return entity;
    }

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
	public List<ServiceOrderVO> getServiceOrdersVO() {
		return serviceOrdersVO;
	}
	public void setServiceOrdersVO(List<ServiceOrderVO> serviceOrdersVO) {
		this.serviceOrdersVO = serviceOrdersVO;
	}
	public List<PhoneVO> getPhonesVO() {
		return phonesVO;
	}
	public void setPhonesVO(List<PhoneVO> phonesVO) {
		this.phonesVO = phonesVO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientVO other = (ClientVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
