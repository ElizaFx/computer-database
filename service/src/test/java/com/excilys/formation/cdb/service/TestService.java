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
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.util.Utils;

@ContextConfiguration("/testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestService {

	private IComputerService computerService = Utils.context
			.getBean(IComputerService.class);

	private ICompanyService companyService = Utils.context
			.getBean(ICompanyService.class);

	@Before
	public void setUp() throws IOException {
		new Utils().loadDatabase();
	}

	@After
	public void reset() throws IOException {
		new Utils().unloadDatabase();
	}

	@Test
	public void findAllComputer() {
		assertEquals(273, computerService.findAll().size());
		assertEquals(273, computerService.count());
	}

	@Test
	public void findAllCompanies() {

		assertEquals(42, companyService.findAll().size());
		assertEquals(42, companyService.count());
	}

	@Test
	public void findComputer() throws ParseException {
		ComputerDTO elfII = new ComputerDTO();
		elfII.setId(20l);
		elfII.setName("ELF II");
		elfII.setIntroduced("01/01/1977");
		elfII.setDiscontinued("");
		elfII.setCompanyId(4l);
		elfII.setCompanyName("Netronics");
		ComputerDTO computer = computerService.find(20l);
		assertNotNull(computer);
		System.out.println(computer);
		System.out.println(elfII);
		assertTrue(computer.equals(elfII));
		assertNull(computerService.find(-1l));
	}

	@Test
	public void findCompany() {
		Company sony = new Company();
		sony.setId(17l);
		sony.setName("Sony");

		Company company = companyService.find(17l);
		assertNotNull(company);
		assertTrue(company.equals(sony));

		assertNull(companyService.find(2000l));
	}

	@Test
	public void createUpdateRemoveComputer() {
		{
			Company company = companyService.find(17l);
			computerService.insert(ComputerDTO.build().name("Joxit")
					.companyId(company.getId()).companyName(company.getName())
					.create());
		}
		ComputerDTO jox = computerService.find(c -> (c.getName() != null)
				&& c.getName().equals("Joxit"));

		assertNotNull(jox);
		assertNull(computerService.find(c -> c.getName().equals("Joxit42")));
		jox.setName("Joxit42");
		computerService.update(jox);
		assertNotNull(computerService.find(c -> c.getName().equals("Joxit42")));

		computerService.remove(jox);
		assertNull(computerService.find(c -> c.getName().equals("Joxit")));
	}

	@Test
	public void deleteCompany() {
		List<ComputerDTO> computers = computerService.findAllByCompany(1l);
		assertTrue(computers.stream().allMatch(
				c -> computerService.find(c.getId()) != null));
		assertNotNull(companyService.find(1l));
		companyService.remove(1l);
		assertNull(companyService.find(1l));
		assertTrue(computers.stream().allMatch(
				c -> computerService.find(c.getId()) == null));
	}

	@Test(expected = DAOException.class)
	public void invalidCreation() {
		computerService.insert(null);
	}

	@Test(expected = DAOException.class)
	public void invalidComputerFind() {
		computerService.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidCompanyFind() {
		companyService.find((Long) null);
	}

	@Test(expected = DAOException.class)
	public void invalidUpdate() {
		computerService.update(null);
	}

	@Test(expected = DAOException.class)
	public void invalidRemove() {
		computerService.remove((ComputerDTO) null);
	}

}
