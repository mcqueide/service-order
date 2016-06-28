package br.com.codeshare.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.codeshare.annotation.validator.PhoneLengthPattern;

public class PhoneLengthValidator implements ConstraintValidator<PhoneLengthPattern, String>{

	@Override
	public void initialize(PhoneLengthPattern constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!(value.isEmpty()) && (value.length() < 13) || (value.length() > 14) )
			return false;
		return true;
	}

}
