package com.excilys.formation.cdb.ui.cmd;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;

public class CreateComputerCmd implements ICommand {

	@Autowired
	private ComputerService computerService;
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
		if (computerService.insert(computer) == 1) {
			System.out.println("Entry insered." + computer);
		} else {
			System.out.println("An error has occured, entry not insered");
		}
	}
}