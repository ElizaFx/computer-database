package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.ui.CLI;

public class DeleteCompanyCmd implements ICommand {

	private ICompanyService companyService = CLI.context.getBean(
			"companyService", ICompanyService.class);
	private final Company company;

	public DeleteCompanyCmd(Company company) {
		this.company = company;
	}

	public DeleteCompanyCmd(Long id) {
		company = companyService.find(id);
	}

	@Override
	public void execute() {
		if (company == null) {
			System.out.println("Delete failed : Company is null");
		} else if (companyService.remove(company.getId()) == 1) {
			System.out.println("Entry deleted." + company);
		} else {
			System.out
					.println("An error has occured, entry not deleted."
							+ ((company.getId() == null) ? " Maybe because of the null id"
									: ""));
		}
	}

}
