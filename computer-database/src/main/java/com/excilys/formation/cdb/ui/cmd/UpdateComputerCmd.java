package com.excilys.formation.cdb.ui.cmd;

import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class UpdateComputerCmd implements ICommand {
	private final Computer computer;

	public UpdateComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public UpdateComputerCmd(Long id, String name, LocalDate introduced,
			LocalDate discontinued, Company company) {
		this(new Computer(id, name, introduced, discontinued, company));
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Update failed : Computer is null");
		}
		if (ComputerDAO.INSTANCE.update(computer) == 1) {
			System.out.println("Entry updated." + computer);
		} else {
			System.out
					.println("An error has occured, entry not updated."
							+ ((computer.getId() == null) ? " Maybe because of the null id"
									: ""));
		}
	}
}
