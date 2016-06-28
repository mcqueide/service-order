package br.com.codeshare.annotation.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.codeshare.validator.PhoneLengthValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneLengthValidator.class)
@Documented
public @interface PhoneLengthPattern {

	int min() default 0;

	int max() default Integer.MAX_VALUE;
	
	String message() default "{phone.invalid.length}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
