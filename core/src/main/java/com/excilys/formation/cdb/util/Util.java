package com.excilys.formation.cdb.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class Util {

	public static boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		return s.matches("\\d+") && (s.length() < 10);
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

}
