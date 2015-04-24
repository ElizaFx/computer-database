package com.excilys.formation.cdb.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
	public static boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		return s.matches("\\d+") && (s.length() < 10);
	}

	public static LocalDate parseDate(String s) {
		if (s.matches("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")) {
			return LocalDate.parse(s.replaceAll("[/.]", "-"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} else if (s.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
			return LocalDate.parse(s.replaceAll("[/.]", "-"),
					DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return null;
	}

	public static boolean isDate(String s) {
		if ((s != null)
				&& (s.matches("^\\d{4}([/.-])\\d{2}\\1\\d{2}$") || s
						.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$"))) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
			sdf1.setLenient(false);
			sdf2.setLenient(false);
			if ((sdf1.parse(s.replaceAll("[/.]", "-"), new ParsePosition(0)) != null)
					|| (sdf2.parse(s.replaceAll("[/.]", "-"),
							new ParsePosition(0)) != null)) {
				return true;
			}
		}
		return false;
	}

	public static String formatDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static java.sql.Date toSqlDate(LocalDate d) {
		if (d == null) {
			return null;
		}
		return java.sql.Date.valueOf(Util.formatDate(d));
	}

	public static String trim(String s) {
		return s == null ? null : s.trim();
	}

}
