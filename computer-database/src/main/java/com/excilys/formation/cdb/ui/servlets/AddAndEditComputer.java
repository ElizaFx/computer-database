package com.excilys.formation.cdb.ui.servlets;

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
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

/**
 * Servlet implementation class EditComputer
 */
@Controller
public class AddAndEditComputer {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/editComputer", method = RequestMethod.GET)
	protected String doEditGet(
			@RequestParam(value = "id", required = false) Long id,
			ModelMap model) {
		return processGet("editComputer", id, model);
	}

	@RequestMapping(value = "/addComputer", method = RequestMethod.GET)
	protected String doAddGet(ModelMap model) {
		return processGet("addComputer", null, model);
	}

	private String processGet(String path, Long id, ModelMap model) {
		if ("editComputer".equals(path)) {
			Computer computer = id == null ? null : computerService.find(id);
			if (computer != null) {
				model.addAttribute("computer", ComputerMapper.toDTO(computer));
			} else {
				model.addAttribute("danger",
						"Error: This computer does't exist!");
			}
		}

		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return path;
	}

	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	protected String doEditPost(@ModelAttribute ComputerDTO computer,
			BindingResult result, ModelMap model) {
		return processPost("editComputer", computer, result, model);
	}

	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	protected String doAddPost(@ModelAttribute ComputerDTO computer,
			BindingResult result, ModelMap model) {
		return processPost("addComputer", computer, result, model);
	}

	private String processPost(String path, ComputerDTO computer,
			BindingResult result, ModelMap model) {
		computer.validate(result);
		boolean hasErrors = result.hasErrors();
		boolean addComputer = "addComputer".equals(path);
		boolean process = addComputer
				|| (computerService.find(computer.getId()) != null);
		if (!hasErrors && process) {
			if (addComputer) {
				computerService.insert(ComputerMapper.toModel(computer));
			} else {
				computerService.update(ComputerMapper.toModel(computer));
			}
			model.addAttribute("success", "Computer " + computer.getName()
					+ (addComputer ? " added" : " edited"));
		} else {
			model.addAttribute("danger", "Error: Check red labels!");
		}

		if (process && (!addComputer || hasErrors)) {
			model.addAttribute("computer", computer);
		} else if (!addComputer) {
			model.addAttribute("danger", "Error: This computer does't exist!");
		}

		model.addAttribute("errors", result);
		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return path;
	}
}
