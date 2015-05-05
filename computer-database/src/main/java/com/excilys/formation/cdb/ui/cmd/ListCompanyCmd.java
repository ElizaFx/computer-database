package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.ui.CLI;

public class ListCompanyCmd implements ICommand {
	private ICompanyService companyService = (ICompanyService) CLI.context
			.getBean("companyService");

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		companyService.findAll().forEach(e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public String format(Company entity) {
		return String.format("%3d : %s", entity.getId(), entity.getName());
	}
}
