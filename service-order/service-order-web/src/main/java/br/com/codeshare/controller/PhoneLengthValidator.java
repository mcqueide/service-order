package br.com.codeshare.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.com.codeshare.enums.ErrorCode;
import br.com.codeshare.util.WebResources;

@Named
public class PhoneLengthValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String phoneNumber = (String) value;
		
		if(!phoneNumber.isEmpty()){
			if(phoneNumber.length() < 13 || phoneNumber.length() >14){
				FacesMessage message = new FacesMessage(WebResources.getMessage(ErrorCode.LENGTH_MUST_BE_BETWEEN_13_14.getErrorCode()));
				new ValidatorException(message);
			}
		}
	}

}
