package com.excilys.formation.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DateLocaleMapper {

	@Autowired
	private MessageSource messageSource;

	public DateLocaleMapper() {

	}

	public String toString(LocalDate localDate) {
		if (localDate == null) {
			return "";
		}
		String format = messageSource.getMessage("global.datePattern", null,
				LocaleContextHolder.getLocale());
		return localDate.format(DateTimeFormatter.ofPattern(format));
	}

	public LocalDate toLocalDate(String s) {
		if (s.isEmpty() || (s == null)) {
			return null;
		}
		String format = messageSource.getMessage("global.datePattern", null,
				LocaleContextHolder.getLocale());
		return LocalDate.parse(s, DateTimeFormatter.ofPattern(format));
	}
}
