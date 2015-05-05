package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.Page;
import com.excilys.formation.cdb.validation.LongSelectionValidator;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/dashboard")
@Controller
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerService computerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Page<ComputerDTO> pagined = computerService.getPage(
				request.getParameter("search"), request.getParameter("limit"),
				request.getParameter("page"), request.getParameter("orderBy"),
				request.getParameter("asc"));

		request.setAttribute("pagined", pagined);
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LongSelectionValidator ids = new LongSelectionValidator(
				request.getParameter("selection"));

		if (ids.isValid()) {
			computerService.remove(ids.getOutput());
			request.setAttribute("success", "Computers deleted");
		} else {
			request.setAttribute("danger", "Error: " + ids.getMsg());
		}
		doGet(request, response);
	}
}
