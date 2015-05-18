package com.excilys.formation.cdb.ui.cmd;

import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.ui.CLI;

public class CreateComputerCmd implements ICommand {

	private IComputerService computerService = (IComputerService) CLI.context
			.getBean("computerService");
	private final Computer computer;

	public CreateComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public CreateComputerCmd(String name, LocalDate introduced,
			LocalDate discontinued, Company company) {
		this(new Computer(name, introduced, discontinued, company));
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Create failed : Computer is null");
		}
		computerService.insert(computer);
		System.out.println("Entry insered." + computer);

	}
}