package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;
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
		String search = request.getParameter("search");
		OrderBy ob = OrderBy.map(request.getParameter("orderBy"));
		boolean asc = Boolean.parseBoolean(request.getParameter("asc"));
		if (search == null) {
			search = "";
		}
		int count = ComputerService.getInstance().count(search);
		int curPage = 1;
		int limit = 10;
		if (Util.isNumeric(sPage)) {
			curPage = Integer.parseInt(sPage);
		}
		if (Util.isNumeric(sLimit)) {
			limit = Integer.parseInt(sLimit);
		}

		Page<Computer> pagined = new Page<>(ComputerService.getInstance(),
				search, count, curPage, limit, 5, ob, asc);

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
		String sIds = request.getParameter("selection");
		String[] ids = sIds == null ? null : sIds.split(",");
		StringBuilder messageError = new StringBuilder();
		if ((ids != null) && (ids.length != 0)) {
			if (Arrays.stream(ids).allMatch(id -> Util.isNumeric(id))) {
				Arrays.stream(ids).map(id -> Long.parseLong(id)).forEach(c -> {
					if (c != null) {
						ComputerService.getInstance().remove(c);
					}
				});
			} else {
				messageError
						.append("Something goes wrong with your selection, wrong ids");
			}
		} else {
			messageError.append("You need to choose some computers first");
		}

		if (messageError.length() == 0) {
			request.setAttribute("success", "Computers deleted");
		} else {
			request.setAttribute("danger", "Error: " + messageError.toString());
		}
		doGet(request, response);
	}
}
