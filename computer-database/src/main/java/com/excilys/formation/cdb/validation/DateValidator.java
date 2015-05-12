package com.excilys.formation.cdb.validation;

import java.time.LocalDate;

import com.excilys.formation.cdb.util.Util;

public class DateValidator extends Validate<LocalDate> {
	private LocalDate date;

	public DateValidator(String s) {
		super(s);
		if (Util.isDate(getInput())) {
			date = Util.parseDate(getInput());
			if ((date != null)
					&& ((date.toEpochDay() < 0) || (date.toEpochDay() > Integer.MAX_VALUE))) {
				setWarningMsg("error.badDate");
				date = null;
			}
		} else if (hasInput()) {
			setErrorMsg("error.malformedDate");
		}
	}

	@Override
	public LocalDate getOutput() {
		return date;
	}

}
