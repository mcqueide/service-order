package br.com.codeshare.vo;

import br.com.codeshare.enums.PhoneState;

/**
 * Created by mcqueide on 28/02/17.
 */
public class PhoneVO {

    private Long id;
    private String brand;
    private String model;
    private PhoneState state;
    private String esn;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneVO phoneVO = (PhoneVO) o;

        if (id != null ? !id.equals(phoneVO.id) : phoneVO.id != null) return false;
        if (brand != null ? !brand.equals(phoneVO.brand) : phoneVO.brand != null) return false;
        if (model != null ? !model.equals(phoneVO.model) : phoneVO.model != null) return false;
        if (state != phoneVO.state) return false;
        return esn != null ? esn.equals(phoneVO.esn) : phoneVO.esn == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (esn != null ? esn.hashCode() : 0);
        return result;
    }
}
