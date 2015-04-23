package com.excilys.formation.cdb.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Util {
	public static boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		return s.matches("\\d+") && (s.length() < 10);
	}

	public static LocalDateTime parseDate(String s) {
		try {
			if (s.matches("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Instant t = Instant.ofEpochMilli(dateFormat.parse(
						s.replaceAll("[/.]", "-")).getTime());
				return LocalDateTime.ofInstant(t, ZoneOffset.UTC);
			} else if (s.matches("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Instant t = Instant.ofEpochMilli(dateFormat.parse(
						s.replaceAll("[/.]", "-")).getTime());
				return LocalDateTime.ofInstant(t, ZoneOffset.UTC);
			}
			return null;
		} catch (ParseException e) {
			throw new RuntimeException("FATAL ERROR in parsing date " + s);
		}
	}

	public static boolean isDate(String s) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		sdf1.setLenient(false);
		sdf2.setLenient(false);
		if ((s != null)
				&& ((sdf1.parse(s, new ParsePosition(0)) != null) || (sdf2
						.parse(s, new ParsePosition(0)) != null))) {
			return true;
		}
		return false;
	}

	public static String formatDate(LocalDateTime d) {
		if (d == null) {
			return null;
		}
		return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static java.sql.Date toSqlDate(LocalDateTime d) {
		if (d == null) {
			return null;
		}
		return java.sql.Date.valueOf(Util.formatDate(d));
	}

	public static String trim(String s) {
		return s == null ? null : s.trim();
	}

}
