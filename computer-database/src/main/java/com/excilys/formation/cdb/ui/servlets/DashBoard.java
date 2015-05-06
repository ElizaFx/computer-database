package com.excilys.formation.cdb.ui.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.Page;
import com.excilys.formation.cdb.validation.LongSelectionValidator;

/**
 * Servlet implementation class Servlet
 */
@Controller
@RequestMapping({ "/dashboard", "/" })
public class DashBoard {
	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "asc", required = false) String asc,
			ModelMap model) {
		System.out.println("sa mere");
		Page<ComputerDTO> pagined = computerService.getPage(search, limit,
				page, orderBy, asc);

		model.addAttribute("pagined", pagined);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "selection", required = true) String selection,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "asc", required = false) String asc,
			ModelMap model) {
		LongSelectionValidator ids = new LongSelectionValidator(selection);

		if (ids.isValid()) {
			computerService.remove(ids.getOutput());
			model.addAttribute("success", "Computers deleted");
		} else {
			model.addAttribute("danger", "Error: " + ids.getMsg());
		}
		return doGet(search, limit, page, orderBy, asc, model);
	}
}
