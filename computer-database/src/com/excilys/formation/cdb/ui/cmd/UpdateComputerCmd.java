package com.excilys.formation.cdb.ui.cmd;

import java.sql.Timestamp;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class UpdateComputerCmd implements Command {
	private final Computer computer;

	public UpdateComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public UpdateComputerCmd(Long id, String name, Timestamp introduced,
			Timestamp discontinued, Long companyId) {
		this(new Computer(id, name, introduced, discontinued, companyId));
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Update failed : Computer is null");
		}
		if (ComputerDAO.getInstance().update(computer) == 1) {
			System.out.println("Entry updated." + computer);
		} else {
			System.out
					.println("An error has occured, entry not updated."
							+ ((computer.getId() == null) ? " Maybe because of the null id"
									: ""));
		}
	}
}
