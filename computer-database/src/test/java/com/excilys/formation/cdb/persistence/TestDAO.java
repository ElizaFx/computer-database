package com.excilys.formation.cdb.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.util.Utils;

/**
 *
 * @author Joxit
 */
@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDAO {

	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;

	@BeforeClass
	public static void setUp() throws IOException {
		new Utils().loadDatabase();
	}

	@AfterClass
	public static void reset() throws IOException {
		new Utils().unloadDatabase();
	}

	@Test
	public void findAllComputer() {
		assertEquals(273, computerDAO.findAll().size());
		assertEquals(273, computerDAO.count());
		assertEquals(30, computerDAO.findAllByCompany(1l).size());
	}

	@Test
	public void findAllCompanies() {
		assertEquals(42, companyDAO.findAll().size());
		assertEquals(42, companyDAO.count());
	}

	@Test
	public void findComputer() throws ParseException {
		Computer elfII = new Computer();
		elfII.setId(20l);
		elfII.setName("ELF II");
		elfII.setIntroduced(LocalDate.of(1977, 1, 1));
		Company netronics = new Company();
		netronics.setId(4l);
		netronics.setName("Netronics");
		elfII.setCompany(netronics);
		Computer computer = computerDAO.find(20l);
		assertNotNull(computer);
		assertTrue(computer.equals(elfII));
		assertNull(computerDAO.find(-1l));
	}

	@Test
	public void findCompany() {

		Company sony = new Company();
		sony.setId(17l);
		sony.setName("Sony");

		Company company = companyDAO.find(17l);
		assertNotNull(company);
		assertTrue(company.equals(sony));

		assertNull(companyDAO.find(2000l));
	}

	@Test
	public void createUpdateRemoveComputer() {
		computerDAO.insert(new Computer("Joxit", null, null, companyDAO
				.find(17l)));

		Computer jox = computerDAO.find(c -> (c.getName() != null)
				&& c.getName().equals("Joxit"));

		assertNotNull(jox);
		assertNull(computerDAO.find(c -> c.getName().equals("Joxit42")));
		jox.setName("Joxit42");

		computerDAO.update(jox);
		assertNotNull(computerDAO.find(c -> c.equals(jox)));

		computerDAO.remove(jox.getId());
		assertNull(computerDAO.find(c -> c.equals(jox)));
		assertNull(computerDAO.find(c -> c.getName().equals("Joxit")));
	}

	@Test
	public void deleteCompany() {
		assertNotNull(companyDAO.find(43l));
		companyDAO.remove(43l);
		assertNull(companyDAO.find(43l));
	}

	@Test(expected = DAOException.class)
	public void invalidCreation() {
		computerDAO.insert(null);
	}

	@Test(expected = DAOException.class)
	public void invalidComputerFind() {
		computerDAO.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidUpdate() {
		computerDAO.update(null);
	}

	@Test(expected = DAOException.class)
	public void invalidRemove() {
		computerDAO.remove(null);
	}

	@Test(expected = DAOException.class)
	public void invalidCompanyFind() {
		companyDAO.find((Long) null);
	}

	@Test(expected = NullPointerException.class)
	public void invalidCompanyModel() {
		assertNull(CompanyMapper.toModel((CompanyDTO) null));
	}

	@Test(expected = NullPointerException.class)
	public void invalidComputerModel() {
		assertNull(ComputerMapper.toModel((ComputerDTO) null));
	}

	@Test
	public void removeComputerByCompany() {
		List<Computer> computers = computerDAO.findAllByCompany(17l);
		assertEquals(computers.size(), computerDAO.removeByCompany(17l));
		// Reset DB state
		computers.stream().forEach(computerDAO::insert);
	}
}
