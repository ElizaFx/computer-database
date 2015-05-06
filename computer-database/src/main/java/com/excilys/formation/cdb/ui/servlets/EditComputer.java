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
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.validation.CompanyValidator;
import com.excilys.formation.cdb.validation.ComputerValidator;
import com.excilys.formation.cdb.validation.DateValidator;
import com.excilys.formation.cdb.validation.NameValidator;

/**
 * Servlet implementation class EditComputer
 */
@Controller
@RequestMapping("/editComputer")
public class EditComputer {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "id", required = false) String id,
			ModelMap model) throws ServletException, IOException {
		ComputerValidator computer = new ComputerValidator(id);

		if (computer.isValid()) {
			model.addAttribute("computer",
					ComputerMapper.toDTO(computer.getOutput()));
		} else {
			model.addAttribute("danger", "Error: This computer does't exist!");
		}

		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "id", required = true) String pId,
			@RequestParam(value = "computerName", required = true) String pComputerName,
			@RequestParam(value = "introduced", required = false) String pIntroduced,
			@RequestParam(value = "discontinued", required = false) String pDiscontinued,
			@RequestParam(value = "companyId", required = false) String pCompanyId,
			ModelMap model) throws ServletException, IOException {

		NameValidator computerName = new NameValidator(pComputerName);
		DateValidator introduced = new DateValidator(pIntroduced);
		DateValidator discontinued = new DateValidator(pDiscontinued);
		CompanyValidator company = new CompanyValidator(pCompanyId);
		ComputerValidator computer = new ComputerValidator(pId);
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
			model.addAttribute("discontinuedClass", "has-error");
			model.addAttribute("discontinuedMessage", discontinued.getMsg());
			hasError = true;
		}
		if (!company.isValid()) {
			model.addAttribute("companyClass", "has-error");
			model.addAttribute("companyMessage", company.getMsg());
			hasError = true;
		}
		if (!hasError) {
			computer.getOutput().setName(computerName.getOutput());
			computer.getOutput().setIntroduced(introduced.getOutput());
			computer.getOutput().setDiscontinued(discontinued.getOutput());
			computer.getOutput().setCompany(company.getOutput());
			computerService.update(computer.getOutput());
			model.addAttribute("success",
					"Computer " + computerName.getOutput() + " edited");
		} else {
			model.addAttribute("id", pId);
			model.addAttribute("computerName", pComputerName);
			model.addAttribute("introduced", pIntroduced);
			model.addAttribute("discontinued", pDiscontinued);
			model.addAttribute("companyId", pCompanyId);
			model.addAttribute("danger", "Error: Check red labels!");
		}
		if (computer.isValid()) {
			model.addAttribute("computer",
					ComputerMapper.toDTO(computer.getOutput()));
		} else {
			model.addAttribute("danger", "Error: This computer does't exist!");
		}
		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return "editComputer";
	}
}
