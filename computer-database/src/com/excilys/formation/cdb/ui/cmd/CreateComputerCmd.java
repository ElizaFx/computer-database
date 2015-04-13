package com.excilys.formation.cdb.ui.cmd;

import java.sql.Timestamp;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class CreateComputerCmd implements Command {

	private final Computer computer;

	public CreateComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public CreateComputerCmd(String name, Timestamp introduced,
			Timestamp discontinued, Long companyId) {
		this(new Computer(name, introduced, discontinued, companyId));
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