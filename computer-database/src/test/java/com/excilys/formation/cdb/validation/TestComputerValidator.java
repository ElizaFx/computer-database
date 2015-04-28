package com.excilys.formation.cdb.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.cdb.Utils;

public class TestComputerValidator {
	@BeforeClass
	public static void setUp() throws IOException {
		Utils.loadDatabase();
	}

	@AfterClass
	public static void reset() throws IOException {
		Utils.unloadDatabase();
	}

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
