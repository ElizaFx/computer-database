package com.excilys.formation.cdb.ui;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.util.Util;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGetAndPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lCompanies", CompanyMapper
				.companyModelToDTO(CompanyService.getInstance().findAll()));

		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/addComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGetAndPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("addComputer") != null) {
			System.out.println("je suis le roi du monde ? ");
		}
		System.out.println(request.getParameterMap());

		String sComputerName = request.getParameter("computerName");
		String sIntroduced = request.getParameter("introduced");
		String sDiscontinued = request.getParameter("discontinued");
		String sCompanyId = request.getParameter("companyId");

		boolean fail = false;

		Date introduced = null;
		Date discontinued = null;
		long companyId = 0;

		if ((sComputerName == null) || sComputerName.isEmpty()) {
			System.out.println(sComputerName);
			fail = true;
			request.setAttribute("computerNameClass", "has-error");
		}

		if (Util.isDate(sIntroduced)) {
			System.out.println(sIntroduced);
			introduced = Util.parseDate(sIntroduced);
		} else if ((sIntroduced != null) && !sIntroduced.isEmpty()) {
			fail = true;
			request.setAttribute("introducedClass", "has-error");
		}
		if (Util.isDate(sDiscontinued)) {
			System.out.println(sDiscontinued);
			discontinued = Util.parseDate(sDiscontinued);
		} else if ((sDiscontinued != null) && !sDiscontinued.isEmpty()) {
			fail = true;
			request.setAttribute("introducedClass", "has-error");
		}
		if (Util.isNumeric(sCompanyId)) {
			System.out.println(sCompanyId);
			companyId = Integer.parseInt(sCompanyId);
		} else if ((sCompanyId != null) && !sCompanyId.isEmpty()) {
			fail = true;
			request.setAttribute("companyIdClass", "has-error");
		}
		if (!fail) {
			Computer computer = new Computer();
			computer.setName(sComputerName);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany(CompanyService.getInstance().find(companyId));
			ComputerService.getInstance().insert(computer);
		}
		System.out.println(fail);
		doGetAndPost(request, response);
	}
}
