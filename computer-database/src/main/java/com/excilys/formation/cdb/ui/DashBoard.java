package com.excilys.formation.cdb.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.service.ComputerService;
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
		List<Integer> lPages = new ArrayList<Integer>();
		int count = ComputerService.getInstance().count();
		int curPage = 1;
		int limit = 10;
		if (Util.isNumeric(sPage)) {
			curPage = Integer.parseInt(sPage);
		}
		if (Util.isNumeric(sLimit)) {
			limit = Integer.parseInt(sLimit);
			if (limit < 1) {
				limit = 10;
			}
		}
		if (((curPage - 1) * limit) > count) {
			curPage = 1;
		}

		int pageMax = (count / limit) + 1;
		int firstPage = ((curPage - 2) < 1) ? 1 : (curPage - 2);
		int lastPage = (firstPage + 4) > pageMax ? pageMax : (firstPage + 4);
		firstPage = lastPage - 4;
		System.out.println(firstPage + " " + pageMax + " " + lastPage + " "
				+ curPage + " " + limit);
		for (; firstPage <= lastPage; firstPage++) {
			lPages.add(firstPage);
		}
		int previousPage = (curPage - 1) < 1 ? 1 : curPage - 1;
		int next = (curPage + 1) > pageMax ? pageMax : curPage + 1;
		System.out.println(request.getParameterMap());

		request.setAttribute("debug", request.getParameterMap());
		request.setAttribute("previousPage", previousPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("nextPage", next);
		request.setAttribute("curLimit", limit);
		request.setAttribute("nbComputers", count);
		request.setAttribute("lPages", lPages);
		request.setAttribute("lComputers", ComputerService.getInstance()
				.pagination(limit, ((curPage - 1) * limit)));
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
