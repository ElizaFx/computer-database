package com.excilys.formation.cdb.ui.controllers;

import java.util.List;

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
import com.excilys.formation.cdb.service.impl.Pagination;

/**
 * Servlet implementation class Servlet
 */
@Controller
@RequestMapping({ "/dashboard", "/" })
public class DashBoard {
	@Autowired
	private IComputerService computerService;

	/**
	 * @param mPage
	 *            validated for pagination
	 * @param result
	 * @param model
	 * @return dashboard
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@ModelAttribute Pagination<ComputerDTO> mPage,
			BindingResult result, ModelMap model) {
		mPage.setPaginable(computerService);

		mPage.validate();
		model.addAttribute("pagined", mPage);
		return "dashboard";
	}

	/**
	 * 
	 * @param selection
	 *            of computer to delete
	 * @param mPage
	 *            validated for pagination
	 * @param result
	 * @param model
	 * @return dashboard
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "selection", required = true) List<Long> selection,
			@ModelAttribute Pagination<ComputerDTO> mPage,
			BindingResult result, ModelMap model) {
		computerService.remove(selection);
		model.addAttribute("success", "Computers deleted");

		return doGet(mPage, result, model);
	}
}
