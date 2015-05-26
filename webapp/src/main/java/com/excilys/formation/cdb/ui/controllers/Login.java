package com.excilys.formation.cdb.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected ModelAndView doLoginGet(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "login.error");
		}

		if (logout != null) {
			model.addObject("logout", "login.logout");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ModelAndView doLoginPost(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			ModelMap model) {
		return new ModelAndView("login", model);
	}
}
