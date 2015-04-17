package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.Page;
import com.excilys.formation.cdb.util.Util;

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

		String sPage = request.getParameter("page");
		String sLimit = request.getParameter("limit");
		int count = ComputerService.getInstance().count();
		int curPage = 1;
		int limit = 10;
		if (Util.isNumeric(sPage)) {
			curPage = Integer.parseInt(sPage);
		}
		if (Util.isNumeric(sLimit)) {
			limit = Integer.parseInt(sLimit);
		}

		Page<Computer> pagined = new Page<>(ComputerService.getInstance(),
				count, curPage, limit, 5);
		System.out.println(request.getParameterMap());
		request.setAttribute("pagined", pagined);
		request.setAttribute("lComputers",
				ComputerMapper.computerModelToDTO(pagined.getPage()));
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
		// TODO Auto-generated method stub
	}

}
