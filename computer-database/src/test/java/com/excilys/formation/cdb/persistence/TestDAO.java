package com.excilys.formation.cdb.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.cdb.Utils;
import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.validation.DateValidator;

/**
 *
 * @author Joxit
 */
public class TestDAO {

	@BeforeClass
	public static void setUp() throws IOException {
		Utils.loadDatabase();
	}

	@AfterClass
	public static void reset() throws IOException {
		Utils.unloadDatabase();
	}

	@Test
	public void findAllComputer() {
		ComputerDAO crf = ComputerDAO.INSTANCE;
		assertEquals(273, crf.findAll().size());
		assertEquals(273, crf.count());
		assertEquals(30, crf.findAllByCompany(1l).size());
	}

	@Test
	public void findAllCompanies() {
		CompanyDAO cyf = CompanyDAO.INSTANCE;
		assertEquals(42, cyf.findAll().size());
		assertEquals(42, cyf.count());
	}

	@Test
	public void findComputer() throws ParseException {
		ComputerDAO crf = ComputerDAO.INSTANCE;
		Computer elfII = new Computer();
		elfII.setId(20l);
		elfII.setName("ELF II");
		elfII.setIntroduced(new DateValidator("1977-01-01").getOutput());
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

	@Test
	public void deleteCompany() {
		assertNotNull(CompanyDAO.INSTANCE.find(43l));
		CompanyDAO.INSTANCE.remove(43l);
		assertNull(CompanyDAO.INSTANCE.find(43l));
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

	@Test(expected = NullPointerException.class)
	public void invalidCompanyModel() {
		assertNull(CompanyMapper.toModel((CompanyDTO) null));
	}

	@Test(expected = NullPointerException.class)
	public void invalidComputerModel() {
		assertNull(ComputerMapper.toModel((ComputerDTO) null));
	}
}
