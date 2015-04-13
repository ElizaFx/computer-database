package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;

public class ListCompanyCmd extends ListCommand<Company> {

	public ListCompanyCmd() {
		super(CompanyDAO.getInstance());
	}

	@Override
	public String format(Company entity) {
		return String.format("%3d : %s", entity.getId(), entity.getName());
	}
}
