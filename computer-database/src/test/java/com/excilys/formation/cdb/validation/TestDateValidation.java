package com.excilys.formation.cdb.validation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class TestDateValidation {
	@Test
	public void validDateValidator() {
		LocalDate expected = LocalDate.of(1993, 2, 17);
		assertEquals(expected, new DateValidator("17/02/1993").getOutput());
		assertEquals(expected, new DateValidator("17-02-1993").getOutput());
		assertEquals(expected, new DateValidator("17.02.1993").getOutput());
		assertEquals(expected, new DateValidator("1993-02-17").getOutput());
		assertEquals(expected, new DateValidator("1993/02/17").getOutput());
		assertEquals(expected, new DateValidator("1993.02.17").getOutput());
	}

	@Test
	public void invalidFormatDateValidator() {
		LocalDate expected = null;
		assertEquals(expected, new DateValidator("17/02-1993").getOutput());
		assertEquals(expected, new DateValidator("17-02.1993").getOutput());
		assertEquals(expected, new DateValidator("17/02.1993").getOutput());
		assertEquals(expected, new DateValidator("1993.02/17").getOutput());
		assertEquals(expected, new DateValidator("1993-02/17").getOutput());
		assertEquals(expected, new DateValidator(null).getOutput());
		assertEquals(expected,
				new DateValidator("this is not a date").getOutput());
	}

	@Test
	public void inexistentDateValidator() {
		LocalDate expected = null;
		assertEquals(expected, new DateValidator("29/02/1993").getOutput());
		assertEquals(expected, new DateValidator("17-02-1393").getOutput());
		assertEquals(expected, new DateValidator("42/12/1993").getOutput());
		assertEquals(expected, new DateValidator("1993/40/17").getOutput());
	}
}
