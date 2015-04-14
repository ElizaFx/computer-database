package com.excilys.formation.cdb.ui.cmd;

import java.util.Date;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class CreateComputerCmd implements ICommand {

	private final Computer computer;

	public CreateComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public CreateComputerCmd(String name, Date introduced, Date discontinued,
			Company company) {
		this(new Computer(name, introduced, discontinued, company));
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Create failed : Computer is null");
		}
		if (ComputerDAO.getInstance().insert(computer) == 1) {
			System.out.println("Entry insered." + computer);
		} else {
			System.out.println("An error has occured, entry not insered");
		}
	}
}