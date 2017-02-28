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
}
