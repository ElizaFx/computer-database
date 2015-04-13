package com.excilys.formation.cdb.ui.cmd;

import java.util.List;

import org.junit.Test;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class TestCmd {

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
		List<Computer> l = ComputerDAO.getInstance().findAll();
		ICommand cmd = new ComputerDetailsCmd(l.get(
				(int) (Math.random() * l.size())).getId());
		cmd.execute();
	}

	@Test
	public void CreateUpdateDeleteComputerTest() {
		ICommand cmd1 = new CreateComputerCmd(new Computer("Joxit", null, null,
				17l));
		cmd1.execute();
		Computer computer = ComputerDAO.getInstance().find(
				c -> c.getName().equals("Joxit"));
		assert (computer != null);
		computer.setName("Joxit42");
		ICommand cmd2 = new UpdateComputerCmd(computer);
		cmd2.execute();
		assert (ComputerDAO.getInstance().find(
				c -> c.getName().equals("Joxit42")) != null);
		assert (ComputerDAO.getInstance()
				.find(c -> c.getName().equals("Joxit")) == null);
		ICommand cmd3 = new DeleteComputerCmd(computer.getId());
		cmd3.execute();
		assert (ComputerDAO.getInstance().find(
				c -> c.getName().equals("Joxit42")) == null);
	}
}
