package com.excilys.formation.cdb.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.util.Util;

public class CompanyValidator extends Validate<Company> {
	@Autowired
	private CompanyService companyService;
	private Company company;

	public CompanyValidator(String input) {
		super(input);
		if (Util.isNumeric(getInput())) {
			long companyId = Long.parseLong(getInput());
			if (companyId != 0) {
				company = companyService.find(companyId);
				if (company == null) {
					setErrorMsg("Incorrect company ID : This company doesn't exist");
				}
			}
		} else if (hasInput()) {
			setErrorMsg("Incorrect company ID : Malformed company ID");
		}
	}

	@Override
	public Company getOutput() {
		return company;
	}

}
