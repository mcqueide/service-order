package br.com.codeshare.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.codeshare.enums.PhoneState;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;

public class PhoneVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String brand;
	
	private String model;
	
	private PhoneState state;
	
	private String esn;
	
	private ClientVO clientVO;
	
	private List<ServiceOrderVO> so;
	
	public PhoneVO() {
	}

    public PhoneVO(Phone entity) {
        this.id = entity.getId();
        this.brand = entity.getBrand();
        this.model = entity.getModel();
        this.state = entity.getState();
        this.esn = entity.getEsn();
        if(entity.getClient() != null){
            this.clientVO = new ClientVO(entity.getClient());
        }

        if(entity.getOs() != null){
            List<ServiceOrderVO> osVO = new ArrayList<>();
            for (ServiceOrder os : entity.getOs()) {
                osVO.add(new ServiceOrderVO(os));
            }
            this.so = osVO;
        }
    }

    public Phone fromDTO(){
        Phone entity = new Phone();

        entity.setId(this.id);
        entity.setBrand(this.brand);
        entity.setModel(this.model);
        entity.setState(this.state);
        entity.setEsn(this.esn);
        if(this.getClientVO() != null){
            entity.setClient(this.clientVO.fromDTO());
        }

        if (this.so != null){
            List<ServiceOrder> so = new ArrayList<>();
            for (ServiceOrderVO soVO : this.so) {
                so.add(soVO.fromDTO());
            }
            entity.setOs(so);
        }

        return entity;
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public PhoneState getState() {
		return state;
	}
	public void setState(PhoneState state) {
		this.state = state;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public ClientVO getClientVO() {
		return clientVO;
	}
	public void setClientVO(ClientVO clientVO) {
		this.clientVO = clientVO;
	}
	public List<ServiceOrderVO> getSo() {
		return so;
	}
	public void setSo(List<ServiceOrderVO> so) {
		this.so = so;
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
		PhoneVO other = (PhoneVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
