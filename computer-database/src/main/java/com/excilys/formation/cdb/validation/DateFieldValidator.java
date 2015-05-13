package com.excilys.formation.cdb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.formation.cdb.util.Util;

public class DateFieldValidator implements
		ConstraintValidator<DateField, String> {

	@Override
	public void initialize(DateField annotation) {
	}

	@Override
	public boolean isValid(String field,
			ConstraintValidatorContext constraintContext) {
		return (field == null) || field.trim().isEmpty() || Util.isDate(field);
	}
}
