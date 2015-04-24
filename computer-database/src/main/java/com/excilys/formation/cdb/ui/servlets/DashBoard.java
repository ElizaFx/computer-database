package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.Page;
import com.excilys.formation.cdb.validation.LongSelectionValidator;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Page<ComputerDTO> pagined = ComputerService.INSTANCE.getPage(
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
			ComputerService.INSTANCE.remove(ids.getOutput());
			request.setAttribute("success", "Computers deleted");
		} else {
			request.setAttribute("danger", "Error: " + ids.getMsg());
		}
		doGet(request, response);
	}
}
