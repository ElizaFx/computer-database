package com.excilys.formation.cdb.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.Utils;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerValidator {
	@BeforeClass
	public static void setUp() throws IOException {
		new Utils().loadDatabase();
	}

	@AfterClass
	public static void reset() throws IOException {
		new Utils().unloadDatabase();
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
