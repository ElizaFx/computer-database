package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.ui.CLI;

public class DeleteComputerCmd implements ICommand {
	private IComputerService computerService = (IComputerService) CLI.context
			.getBean("computerService");
	private final Computer computer;

	public DeleteComputerCmd(Computer computer) {
		this.computer = computer;
	}

	public DeleteComputerCmd(Long id) {
		computer = computerService.find(id);
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Delete failed : Computer is null");
		} else if (computerService.remove(computer.getId()) == 1) {
			System.out.println("Entry deleted." + computer);
		} else {
			System.out
					.println("An error has occured, entry not deleted."
							+ ((computer.getId() == null) ? " Maybe because of the null id"
									: ""));
		}
	}

}
