package com.excilys.formation.cdb.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DateFieldValidator.class)
public @interface DateField {
	String message() default "{com.excilys.formation.cdb.validation.DateField.message}";

	String pattern() default "{com.excilys.formation.cdb.validation.DateField.pattern}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface List {
		DateField[] value();
	}
}
