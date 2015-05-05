package com.excilys.formation.cdb.ui.cmd;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;

public class ListCompanyCmd implements ICommand {
	@Autowired
	private CompanyService companyService;

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
