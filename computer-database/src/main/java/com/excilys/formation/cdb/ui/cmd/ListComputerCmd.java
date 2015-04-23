package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;

public class ListComputerCmd implements ICommand {

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		ComputerService.INSTANCE.findAll().forEach(
				e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public String format(Computer entity) {
		return String.format("%4d : %s", entity.getId(), entity.getName());
	}

}
