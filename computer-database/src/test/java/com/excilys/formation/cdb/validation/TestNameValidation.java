package com.excilys.formation.cdb.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestNameValidation {
	@Test
	public void validNameValidator() {
		String expected = "Joxit est un super pseudo";
		assertEquals(new NameValidator("  " + expected).getOutput(), expected);
		assertEquals(new NameValidator(expected + "  ").getOutput(), expected);
		assertEquals(new NameValidator("  " + expected + " ").getOutput(),
				expected);
	}

	@Test
	public void invalidNameValidator() {
		assertFalse(new NameValidator("  ").isValid());
		assertFalse(new NameValidator(null).isValid());
	}
}
