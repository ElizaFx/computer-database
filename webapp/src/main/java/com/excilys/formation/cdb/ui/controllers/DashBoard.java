package com.excilys.formation.cdb.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.service.Pagination;
import com.excilys.formation.cdb.validation.LongSelectionValidator;

/**
 * Servlet implementation class Servlet
 */
@Controller
@RequestMapping({ "/dashboard", "/" })
public class DashBoard {
	@Autowired
	private IComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@ModelAttribute Pagination<ComputerDTO> mPage,
			BindingResult result, ModelMap model) {
		mPage.setPaginable(computerService);

		mPage.validate(result);
		model.addAttribute("pagined", mPage);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "selection", required = true) String selection,
			@ModelAttribute Pagination<ComputerDTO> mPage,
			BindingResult result, ModelMap model) {
		LongSelectionValidator ids = new LongSelectionValidator(selection);

		if (ids.isValid()) {
			computerService.remove(ids.getOutput());
			model.addAttribute("success", "Computers deleted");
		} else {
			model.addAttribute("danger", "Error: " + ids.getMsg());
		}
		return doGet(mPage, result, model);
	}
}
