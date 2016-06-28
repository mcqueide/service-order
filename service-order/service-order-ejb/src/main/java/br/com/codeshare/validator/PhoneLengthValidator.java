package br.com.codeshare.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.codeshare.annotation.validator.PhoneLengthPattern;

public class PhoneLengthValidator implements ConstraintValidator<PhoneLengthPattern, String>{

	private int min;
	private int max;

	@Override
	public void initialize(PhoneLengthPattern parameters) {
		min = parameters.min();
		max = parameters.max();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value.isEmpty())
			return true;
		
		return value.length()>= min && value.length() <= max;
	}
	
}
