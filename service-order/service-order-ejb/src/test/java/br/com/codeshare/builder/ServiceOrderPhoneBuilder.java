package br.com.codeshare.builder;

import br.com.codeshare.vo.PhoneStateVO;
import br.com.codeshare.vo.PhoneVO;
import br.com.codeshare.vo.ServiceOrderPhoneVO;
import br.com.codeshare.vo.ServiceOrderVO;

/**
 * Created by mcque on 25/03/2017.
 */
public class ServiceOrderPhoneBuilder {

    private ServiceOrderPhoneVO serviceOrderPhoneVO;

    public ServiceOrderPhoneBuilder() {
        this.serviceOrderPhoneVO = new ServiceOrderPhoneVO();
    }

    public ServiceOrderPhoneBuilder withId(Long id) {
        serviceOrderPhoneVO.setId(id);
        return this;
    }

    public ServiceOrderPhoneBuilder withPhoneState(PhoneStateVO phoneState) {
        serviceOrderPhoneVO.setPhoneState(phoneState);
        return this;
    }

    public ServiceOrderPhoneBuilder withPhone(PhoneVO phone) {
        serviceOrderPhoneVO.setPhone(phone);
        return this;
    }

    public ServiceOrderPhoneBuilder withServiceOrder(ServiceOrderVO serviceOrder) {
        serviceOrderPhoneVO.setServiceOrder(serviceOrder);
        return this;
    }

    public ServiceOrderPhoneVO build(){
        return serviceOrderPhoneVO;
    }
}
