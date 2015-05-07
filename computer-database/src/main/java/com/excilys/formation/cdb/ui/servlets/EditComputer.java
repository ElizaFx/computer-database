package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.validation.ComputerValidator;

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
			@RequestParam(value = "id", required = true) String id,
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
	protected String test(@ModelAttribute ComputerDTO computer,
			BindingResult result, ModelMap model) {

		computer.validate(result);
		boolean exist = computerService.find(computer.getId()) != null;
		if (!result.hasErrors() && exist) {
			computerService.update(ComputerMapper.toModel(computer));
			model.addAttribute("success", "Computer " + computer.getName()
					+ " edited");
		} else {
			model.addAttribute("danger", "Error: Check red labels!");
		}
		if (exist) {
			model.addAttribute("computer", computer);
		} else {
			model.addAttribute("danger", "Error: This computer does't exist!");
		}

		model.addAttribute("errors", result);
		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return "editComputer";
	}
}
