package br.com.codeshare.convert;

import br.com.codeshare.service.ClientService;
import br.com.codeshare.vo.ClientVO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ClientConverter implements Converter {

	@Inject
	private ClientService clientService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {				
                return clientService.findById(Long.parseLong(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid client."));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(((ClientVO) object).getId());
        }
        else {
            return null;
        }
	}

}
