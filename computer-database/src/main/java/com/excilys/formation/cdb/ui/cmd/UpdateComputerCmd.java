package com.excilys.formation.cdb.ui.cmd;

import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.ui.CLI;

public class UpdateComputerCmd implements ICommand {
	private IComputerService computerService = (IComputerService) CLI.context
			.getBean("computerService");
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
		computerService.update(computer);
		System.out.println("Entry updated." + computer);

	}
}
