package com.excilys.formation.cdb.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.util.Util;

/**
 *
 * @author Joxit
 */
public class TestDAO {

	@Test
	public void findAllComputer() {
		ComputerDAO crf = ComputerDAO.INSTANCE;
		assertEquals(574, crf.findAll().size());
	}

	@Test
	public void findAllCompanies() {
		CompanyDAO cyf = CompanyDAO.INSTANCE;

		assertEquals(42, cyf.findAll().size());
	}

	@Test
	public void findComputer() throws ParseException {
		ComputerDAO crf = ComputerDAO.INSTANCE;
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
		CompanyDAO cyf = CompanyDAO.INSTANCE;

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
		ComputerDAO crf = ComputerDAO.INSTANCE;
		crf.insert(new Computer("Joxit", null, null, CompanyDAO.INSTANCE
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

	@Test(expected = DAOException.class)
	public void invalidCreation() {
		ComputerDAO.INSTANCE.insert(null);
	}

	@Test(expected = DAOException.class)
	public void invalidComputerFind() {
		ComputerDAO.INSTANCE.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidUpdate() {
		ComputerDAO.INSTANCE.update(null);
	}

	@Test(expected = DAOException.class)
	public void invalidRemove() {
		ComputerDAO.INSTANCE.remove(null);
	}

	@Test(expected = DAOException.class)
	public void invalidCompanyFind() {
		CompanyDAO.INSTANCE.find((Long) null);
	}

	@Test
	public void invalidCompanyModel() {
		CompanyMapper.getModel(null);
	}

	@Test
	public void invalidComputerModel() {
		assertNull(ComputerMapper.getModel(null));
	}
}
