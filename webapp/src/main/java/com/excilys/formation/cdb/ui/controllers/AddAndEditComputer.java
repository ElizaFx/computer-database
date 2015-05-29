package com.excilys.formation.cdb.ui.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;

/**
 * Servlet implementation class EditComputer
 */
@Controller
public class AddAndEditComputer {
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IComputerService computerService;

	@Autowired
	private ComputerMapper computerMapper;

	/**
	 * @param id
	 *            of computer to edit
	 * @param model
	 * @return ModelAndView of computerDTO of this id and editComputer.jsp as
	 *         view
	 */
	@RequestMapping(value = "/editComputer", method = RequestMethod.GET)
	protected ModelAndView doEditGet(
			@RequestParam(value = "id", required = false) Long id,
			ModelMap model) {
		return processGet("editComputer", id, model);
	}

	/**
	 * @param model
	 * @return ModelAndView with empty computerDTO and addComputer as view
	 */
	@RequestMapping(value = "/addComputer", method = RequestMethod.GET)
	protected ModelAndView doAddGet(ModelMap model) {
		return processGet("addComputer", null, model);
	}

	/**
	 * Process for both addComputer and editComputer controller
	 * 
	 * @param path
	 *            view to send
	 * @param id
	 *            of computer if we need (for editComputer)
	 * @param model
	 * @return ModelAndView with path as view
	 */
	private ModelAndView processGet(String path, Long id, ModelMap model) {
		ComputerDTO dto = null;
		if ("editComputer".equals(path)) { // editComputer processing
			dto = id == null ? null : computerService.find(id);
			if (dto == null) {
				dto = new ComputerDTO();
				model.addAttribute("danger", "error.unknownComputer");
			}
		} else { // addComputer processing
			dto = new ComputerDTO();
		}

		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return new ModelAndView(path, "computer", dto);
	}

	/**
	 * 
	 * @param computer
	 *            completed by the user and validated
	 * @param result
	 * @param model
	 * @return editComputer view
	 */
	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	protected String doEditPost(
			@Valid @ModelAttribute("computer") ComputerDTO computer,
			BindingResult result, ModelMap model) {
		return processPost("editComputer", computer, result, model);
	}

	/**
	 * 
	 * @param computer
	 *            completed by the user and validated
	 * @param result
	 * @param model
	 * @return addComputer view
	 */
	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	protected String doAddPost(
			@Valid @ModelAttribute("computer") ComputerDTO computer,
			BindingResult result, ModelMap model) {
		return processPost("addComputer", computer, result, model);
	}

	/**
	 * Process for both addComputer and editComputer controller
	 * 
	 * @param path
	 *            view to send
	 * @param computer
	 *            completed by the user and validated
	 * @param result
	 * @param model
	 * @return ModelAndView with path as view
	 */
	private String processPost(String path, ComputerDTO computer,
			BindingResult result, ModelMap model) {
		boolean hasErrors = result.hasErrors();
		boolean addComputer = "addComputer".equals(path);
		boolean process = addComputer
				|| (computerService.find(computer.getId()) != null);
		if (!hasErrors && process) {
			if (addComputer) {
				computerService.insert(computer);
				model.put("computer", new ComputerDTO());
				model.addAttribute("computerName", computer.getComputerName());
			} else {
				computerService.update(computer);
			}
			model.addAttribute("success", path + ".success");
		} else {
			model.addAttribute("danger", "error.checkRedLabels");
		}

		if (process && (!addComputer || hasErrors)) {
			// editComputer or addComputer with error
			model.addAttribute("computer", computer);
		} else if (!addComputer) { // editComputer
			model.addAttribute("danger", "error.unknownComputer");
		}
		model.addAttribute("errors", result);
		model.addAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));
		return path;
	}
}
