package com.excilys.formation.cdb.ui.cmd;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.util.WebServiceUtils;

@ContextConfiguration("/binding-application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCmd {
	@Autowired
	private ComputerMapper computerMapper;

	@Test
	public void ListComputerTest() {
		ICommand cmd = new ListComputerCmd();
		cmd.execute();
	}

	@Test
	public void ListCompanyTest() {
		ICommand cmd = new ListCompanyCmd();
		cmd.execute();
	}

	@Test
	public void ShowComputerTest() {
		ICommand cmd = new ComputerDetailsCmd(17l);
		cmd.execute();
	}

	@Test
	public void CreateUpdateDeleteComputerTest() {
		CompanyDTO company = WebServiceUtils.getCompany(17l);
		ICommand cmd1 = new CreateComputerCmd(new ComputerDTO(null, "Joxit",
				null, null, company != null ? company.getId() : null,
				company != null ? company.getName() : null));
		cmd1.execute();
		System.err.println("before");
		ComputerDTO computer = WebServiceUtils
				.findAllComputer()
				.stream()
				.filter(c -> (c.getName() != null)
						&& c.getName().equals("Joxit")).findFirst()
				.orElse(null);
		assertNotNull(computer);
		computer.setName("Joxit42");
		ICommand cmd2 = new UpdateComputerCmd(computer);
		cmd2.execute();
		assertNotNull(WebServiceUtils.findAllComputer().stream()
				.filter(c -> "Joxit42".equals(c.getName())).findFirst()
				.orElse(null));
		assertNull(WebServiceUtils.findAllComputer().stream()
				.filter(c -> "Joxit".equals(c.getName())).findFirst()
				.orElse(null));
		ICommand cmd3 = new DeleteComputerCmd(computer.getId());
		cmd3.execute();
		assertNull(WebServiceUtils.findAllComputer().stream()
				.filter(c -> "Joxit42".equals(c.getName())).findFirst()
				.orElse(null));
	}

	@Test
	public void invalidComputerDetail() {
		Long l = -1l;
		ICommand cmd = new ComputerDetailsCmd(l);
		cmd.execute();
	}

}
