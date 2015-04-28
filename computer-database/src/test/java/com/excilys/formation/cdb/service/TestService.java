package com.excilys.formation.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.cdb.Utils;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.util.Util;

public class TestService {

	@Before
	public void setUp() throws IOException {
		Utils.loadDatabase();
	}

	@After
	public void reset() throws IOException {
		Utils.unloadDatabase();
	}

	@Test
	public void findAllComputer() {
		ComputerService crf = ComputerService.INSTANCE;
		assertEquals(273, crf.findAll().size());
		assertEquals(273, crf.count());
	}

	@Test
	public void findAllCompanies() {
		CompanyService cyf = CompanyService.INSTANCE;

		assertEquals(42, cyf.findAll().size());
		assertEquals(42, cyf.count());
	}

	@Test
	public void findComputer() throws ParseException {
		ComputerService crf = ComputerService.INSTANCE;
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
		assertTrue(computer.equals(elfII));
		assertNull(crf.find(-1l));
	}

	@Test
	public void findCompany() {
		CompanyService cyf = CompanyService.INSTANCE;

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
		ComputerService crf = ComputerService.INSTANCE;
		crf.insert(new Computer("Joxit", null, null, CompanyService.INSTANCE
				.find(17l)));

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

	@Test
	public void deleteCompany() {
		List<Computer> computers = ComputerDAO.INSTANCE.findAllByCompany(1l);
		assertTrue(computers.stream().allMatch(
				c -> ComputerService.INSTANCE.find(c.getId()) != null));
		assertNotNull(CompanyService.INSTANCE.find(1l));
		CompanyService.INSTANCE.remove(1l);
		assertNull(CompanyService.INSTANCE.find(1l));
		assertTrue(computers.stream().allMatch(
				c -> ComputerService.INSTANCE.find(c.getId()) == null));
	}

	@Test(expected = DAOException.class)
	public void invalidCreation() {
		ComputerService.INSTANCE.insert(null);
	}

	@Test(expected = DAOException.class)
	public void invalidComputerFind() {
		ComputerService.INSTANCE.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidCompanyFind() {
		CompanyService.INSTANCE.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidUpdate() {
		ComputerService.INSTANCE.update(null);
	}

	@Test(expected = DAOException.class)
	public void invalidRemove() {
		ComputerService.INSTANCE.remove((Long) null);
	}

}
