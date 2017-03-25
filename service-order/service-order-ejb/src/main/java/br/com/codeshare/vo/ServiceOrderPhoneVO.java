package br.com.codeshare.vo;

/**
 * Created by mcque on 25/03/2017.
 */
public class ServiceOrderPhoneVO {

    private Long id;
    private PhoneStateVO phoneState;
    private PhoneVO phone;
    private ServiceOrderVO serviceOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneStateVO getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(PhoneStateVO phoneState) {
        this.phoneState = phoneState;
    }

    public PhoneVO getPhone() {
        return phone;
    }

    public void setPhone(PhoneVO phone) {
        this.phone = phone;
    }

    public ServiceOrderVO getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrderVO serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrderPhoneVO that = (ServiceOrderPhoneVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (phoneState != null ? !phoneState.equals(that.phoneState) : that.phoneState != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return serviceOrder != null ? serviceOrder.equals(that.serviceOrder) : that.serviceOrder == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (phoneState != null ? phoneState.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (serviceOrder != null ? serviceOrder.hashCode() : 0);
        return result;
    }
}
