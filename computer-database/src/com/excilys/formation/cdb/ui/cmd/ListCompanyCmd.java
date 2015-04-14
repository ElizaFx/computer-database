package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;

public class ListCompanyCmd implements ICommand {

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		CompanyDAO.getInstance().findAll()
				.forEach(e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public String format(Company entity) {
		return String.format("%3d : %s", entity.getId(), entity.getName());
	}
}
