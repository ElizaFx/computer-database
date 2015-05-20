package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.ui.CLI;

public class ListComputerCmd implements ICommand {

	private IComputerService computerService = (IComputerService) CLI.context
			.getBean("computerService");

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		computerService.findAll().forEach(e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public String format(Computer entity) {
		return String.format("%4d : %s", entity.getId(), entity.getName());
	}

}
