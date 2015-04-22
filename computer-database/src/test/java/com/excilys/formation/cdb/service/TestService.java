package com.excilys.formation.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.util.Util;

public class TestService {
	@Test
	public void findAllComputer() {
		ComputerService crf = ComputerService.getInstance();

		assertEquals(574, crf.findAll().size());
	}

	@Test
	public void findAllCompanies() {
		CompanyService cyf = CompanyService.getInstance();

		assertEquals(42, cyf.findAll().size());
	}

	@Test
	public void findComputer() throws ParseException {
		ComputerService crf = ComputerService.getInstance();
		Computer elfII = new Computer();
		elfII.setId(20l);
		elfII.setName("ELF II");
		elfII.setIntroduced(Util.parseDate("1977-01-01"));
		Company netronics = new Company();
		netronics.setId(4l);
		netronics.setName("Netronics");
		elfII.setCompany(netronics);
		Computer computer = crf.find(20l);
		assertNotNull(computer);
		assertFalse(computer.equals(elfII));
		assertNull(crf.find(-1l));
	}

	@Test
	public void findCompany() {
		CompanyService cyf = CompanyService.getInstance();

		Company sony = new Company();
		sony.setId(17l);
		sony.setName("Sony");

		Company company = cyf.find(17l);
		assertNotNull(company);
		assertTrue(company.equals(sony));

		assertNull(cyf.find(2000l));
	}

	@Test
	public void createUpdateRemoveComputer() {
		ComputerService crf = ComputerService.getInstance();
		crf.insert(new Computer("Joxit", null, null, CompanyService
				.getInstance().find(17l)));

		Computer jox = crf.find(c -> (c.getName() != null)
				&& c.getName().equals("Joxit"));

		assertNotNull(jox);
		assertNull(crf.find(c -> c.getName().equals("Joxit42")));
		jox.setName("Joxit42");
		crf.update(jox);
		assertNotNull(crf.find(c -> c.equals(jox)));

		crf.remove(jox.getId());
		assertNull(crf.find(c -> c.equals(jox)));
		assertNull(crf.find(c -> c.getName().equals("Joxit")));
	}

	@Test(expected = DAOException.class)
	public void invalidCreation() {
		ComputerService.getInstance().insert(null);
	}

	@Test(expected = DAOException.class)
	public void invalidComputerFind() {
		ComputerService.getInstance().find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidCompanyFind() {
		CompanyService.getInstance().find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidUpdate() {
		ComputerService.getInstance().update(null);
	}

	@Test(expected = DAOException.class)
	public void invalidRemove() {
		ComputerService.getInstance().remove(null);
	}
}
