package com.excilys.formation.cdb.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestComputerValidator {
	@Test
	public void validComputerValidator() {
		assertNotNull(new ComputerValidator("17").getOutput());
	}

	@Test
	public void invalidComputerValidator() {
		assertNull(new ComputerValidator("-1").getOutput());
		assertNull(new ComputerValidator("not a id").getOutput());
		assertNull(new ComputerValidator("4000").getOutput());
	}
}
