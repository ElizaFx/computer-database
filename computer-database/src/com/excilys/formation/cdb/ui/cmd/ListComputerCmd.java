package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class ListComputerCmd implements ICommand {

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		ComputerDAO.getInstance().findAll()
				.forEach(e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public String format(Computer entity) {
		return String.format("%4d : %s", entity.getId(), entity.getName());
	}

}
