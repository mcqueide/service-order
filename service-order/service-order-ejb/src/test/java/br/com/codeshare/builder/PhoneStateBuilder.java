package br.com.codeshare.builder;

import br.com.codeshare.vo.PhoneStateVO;

/**
 * Created by mcque on 25/03/2017.
 */
public class PhoneStateBuilder {

    private PhoneStateVO phoneStateVO;

    public PhoneStateBuilder() {
        this.phoneStateVO = new PhoneStateVO();
    }

    public PhoneStateBuilder withId(Long id) {
        phoneStateVO.setId(id);
        return this;
    }

    public PhoneStateBuilder withState(String state) {
        phoneStateVO.setState(state);
        return this;
    }

    public PhoneStateVO build(){
        return phoneStateVO;
    }

}
