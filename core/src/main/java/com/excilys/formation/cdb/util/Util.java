package com.excilys.formation.cdb.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Util {
	@Autowired
	private MessageSource messageSource;

	public static boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		return s.matches("\\d+") && (s.length() < 10);
	}

	public static LocalDate parseDate(String s) {
		if ((s != null) && !s.isEmpty()) {
			if (s.matches("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")) {
				return LocalDate.parse(s.replaceAll("[/.]", "-"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			} else if (s.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
				return LocalDate.parse(s.replaceAll("[/.]", "-"),
						DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			}
		}
		return null;
	}

	public static boolean isDate(String s) {
		if (s != null) {
			if (s.matches("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.setLenient(false);
				return sdf.parse(s.replaceAll("[/.]", "-"),
						new ParsePosition(0)) != null;
			} else if (s.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				sdf.setLenient(false);
				return sdf.parse(s.replaceAll("[/.]", "-"),
						new ParsePosition(0)) != null;
			}
		}
		return false;
	}

	public static String formatDate(LocalDate localDate) {
		return localDate == null ? null : localDate.format(DateTimeFormatter
				.ofPattern("yyyy-MM-dd"));
	}

	public static java.sql.Date toSqlDate(LocalDate d) {
		return d == null ? null : java.sql.Date.valueOf(Util.formatDate(d));
	}

	public static LocalDate toLocalDate(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toLocalDateTime()
				.toLocalDate();
	}

	public static String trim(String s) {
		return s == null ? null : s.trim();
	}

}
