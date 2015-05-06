package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.validation.CompanyValidator;
import com.excilys.formation.cdb.validation.DateValidator;
import com.excilys.formation.cdb.validation.NameValidator;

/**
 * Servlet implementation class AddComputer
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputer {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model) throws ServletException, IOException {
		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));

		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "computerName", required = true) String pComputerName,
			@RequestParam(value = "introduced", required = false) String pIntroduced,
			@RequestParam(value = "discontinued", required = false) String pDiscontinued,
			@RequestParam(value = "companyId", required = false) String pCompanyId,
			ModelMap model) throws ServletException, IOException {

		NameValidator computerName = new NameValidator(pComputerName);
		DateValidator introduced = new DateValidator(pIntroduced);
		DateValidator discontinued = new DateValidator(pDiscontinued);
		CompanyValidator company = new CompanyValidator(pCompanyId);
		boolean hasError = false;

		if (!computerName.isValid()) {
			model.addAttribute("computerNameClass", "has-error");
			model.addAttribute("nameMessage", computerName.getMsg());
			hasError = true;
		}
		if (!introduced.isValid()) {
			model.addAttribute("introducedClass", "has-error");
			model.addAttribute("introducedMessage", introduced.getMsg());
			hasError = true;
		}
		if (!discontinued.isValid()) {
			model.addAttribute("introducedClass", "has-error");
			model.addAttribute("introducedMessage", discontinued.getMsg());
			hasError = true;
		}
		if (!company.isValid()) {
			model.addAttribute("companyClass", "has-error");
			model.addAttribute("companyMessage", company.getMsg());
			hasError = true;
		}

		if (!hasError) {
			Computer computer = Computer.build().name(computerName.getOutput())
					.introduced(introduced.getOutput())
					.discontinued(discontinued.getOutput())
					.company(company.getOutput()).create();
			computerService.insert(computer);
			model.addAttribute("success",
					"Computer " + computerName.getOutput() + " added");
		} else {
			model.addAttribute("computerName", pComputerName);
			model.addAttribute("introduced", pIntroduced);
			model.addAttribute("discontinued", pDiscontinued);
			model.addAttribute("companyId", pCompanyId);
			model.addAttribute("danger", "Error: Check red labels!");
		}

		return doGet(model);
	}
}
