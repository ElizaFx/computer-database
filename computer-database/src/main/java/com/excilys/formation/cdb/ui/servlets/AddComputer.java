package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.CompanyDTO;
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
	private static final List<CompanyDTO> lCompany = CompanyMapper
			.companyModelToDTO(CompanyService.getInstance().findAll());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
	}

	protected void doGetAndPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lCompanies", lCompany);

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

		String sComputerName = request.getParameter("computerName");
		String sIntroduced = request.getParameter("introduced");
		String sDiscontinued = request.getParameter("discontinued");
		String sCompanyId = request.getParameter("companyId");

		boolean fail = false;

		Date introduced = null;
		Date discontinued = null;
		long companyId = 0;

		if ((sComputerName == null) || sComputerName.trim().isEmpty()) {
			fail = true;
			request.setAttribute("computerNameClass", "has-error");
		} else {
			sComputerName = sComputerName.trim();
		}

		if (Util.isDate(sIntroduced)) {
			introduced = Util.parseDate(sIntroduced);
		} else if ((sIntroduced != null) && !sIntroduced.isEmpty()) {
			fail = true;
			request.setAttribute("introducedClass", "has-error");
		}
		if (Util.isDate(sDiscontinued)) {
			discontinued = Util.parseDate(sDiscontinued);
		} else if ((sDiscontinued != null) && !sDiscontinued.isEmpty()) {
			fail = true;
			request.setAttribute("discontinuedClass", "has-error");
		}
		if (Util.isNumeric(sCompanyId)) {
			companyId = Integer.parseInt(sCompanyId);
			if ((companyId != 0)
					&& (CompanyService.getInstance().find(companyId) == null)) {
				fail = true;
				request.setAttribute("companyIdClass", "has-error");
			}
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
			request.setAttribute("success", "Computer " + sComputerName
					+ " added");
		} else {
			request.setAttribute("computerName",
					request.getParameter("computerName"));
			request.setAttribute("introduced",
					request.getParameter("introduced"));
			request.setAttribute("discontinued",
					request.getParameter("discontinued"));
			request.setAttribute("companyId", request.getParameter("companyId"));
			request.setAttribute("danger", "Error: Check red labels!");
		}

		doGetAndPost(request, response);
	}
}
