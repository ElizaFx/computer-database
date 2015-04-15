package com.excilys.formation.cdb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static boolean isNumeric(String s) {
		return s.matches("\\d+");
	}

	public static Date parseDate(String s) throws ParseException {
		if (s.matches("\\d{4}[/.-]\\d{2}[/.-]\\d{2}")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(s.replaceAll("[/.]", "-"));
		} else if (s.matches("\\d{2}[/.-]\\d{2}[/.-]\\d{4}")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			return dateFormat.parse(s.replaceAll("[/.]", "-"));
		}
		return null;
	}

	public static String formatDate(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(d);
	}

	public static java.sql.Date toSqlDate(Date d) {
		if (d == null) {
			return null;
		}
		return java.sql.Date.valueOf(Util.formatDate(d));
	}

}
