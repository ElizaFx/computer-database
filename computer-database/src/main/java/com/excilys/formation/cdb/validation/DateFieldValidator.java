package com.excilys.formation.cdb.validation;

import java.time.chrono.IsoChronology;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateFieldValidator implements
		ConstraintValidator<DateField, String> {
	private static Logger LOGGER;
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(DateField annotation) {
	}

	@Override
	public boolean isValid(String field,
			ConstraintValidatorContext constraintContext) {

		if ((field == null) || field.trim().isEmpty()) {
			return true;
		}
		String trimed = field.trim();
		if (!trimed.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
			return false;
		}
		String pattern = messageSource.getMessage("global.datePattern", null,
				LocaleContextHolder.getLocale());
		String[] dateSplited = trimed.split("[/.-]");
		String[] patternSplited = pattern.split("[/.-]");
		if (patternSplited.length < 3) {
			LOGGER.error("{} is a bad date pattern", pattern);
			throw new RuntimeException("Bat date pattern");
		}
		return isCorrectDate(toInt(dateSplited, patternSplited, "yyyy"),
				toInt(dateSplited, patternSplited, "MM"),
				toInt(dateSplited, patternSplited, "dd"));
	}

	private boolean isCorrectDate(int year, int month, int dayOfMonth) {
		System.out
				.println("isCorrect " + year + "-" + month + "-" + dayOfMonth);
		if (dayOfMonth > 28) {
			int dom = 31;
			switch (month) {
				case 2:
					dom = (IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					dom = 30;
					break;
			}
			if (dayOfMonth > dom) {
				return false;
			}
		} else if (dayOfMonth < 0) {
			return false;
		}
		if ((month < 0) || (month > 12)) {
			return false;
		}
		if ((year < 1971) || (year > 2037)) {
			return false;
		}
		return true;
	}

	private int toInt(String[] dateSplited, String[] patternSplited,
			String match) {
		for (int i = 0; i < patternSplited.length; i++) {
			if (patternSplited[i].equalsIgnoreCase(match)) {
				return Integer.parseInt(dateSplited[i]);
			}
		}

		return 0;
	}
}
