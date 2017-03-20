package br.com.codeshare.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.vo.PhoneVO;

@Named
public class PhoneConverter implements Converter {

	@Inject
	PhoneService phoneService;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {				
                return phoneService.findById(Long.parseLong(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid phone."));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(((PhoneVO) object).getId());
        }
        else {
            return null;
        }
	}

}
