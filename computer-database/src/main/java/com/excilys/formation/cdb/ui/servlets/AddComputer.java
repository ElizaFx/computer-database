package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.validation.CompanyValidator;
import com.excilys.formation.cdb.validation.DateValidator;
import com.excilys.formation.cdb.validation.NameValidator;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lCompanies",
				CompanyMapper.toDTO(companyService.findAll()));

		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		NameValidator computerName = new NameValidator(
				request.getParameter("computerName"));
		DateValidator introduced = new DateValidator(
				request.getParameter("introduced"));
		DateValidator discontinued = new DateValidator(
				request.getParameter("discontinued"));
		CompanyValidator company = new CompanyValidator(
				request.getParameter("companyId"));
		boolean hasError = false;

		if (!computerName.isValid()) {
			request.setAttribute("computerNameClass", "has-error");
			request.setAttribute("nameMessage", computerName.getMsg());
			hasError = true;
		}
		if (!introduced.isValid()) {
			request.setAttribute("introducedClass", "has-error");
			request.setAttribute("introducedMessage", introduced.getMsg());
			hasError = true;
		}
		if (!discontinued.isValid()) {
			request.setAttribute("introducedClass", "has-error");
			request.setAttribute("introducedMessage", discontinued.getMsg());
			hasError = true;
		}
		if (!company.isValid()) {
			request.setAttribute("companyClass", "has-error");
			request.setAttribute("companyMessage", company.getMsg());
			hasError = true;
		}

		if (!hasError) {
			Computer computer = Computer.build().name(computerName.getOutput())
					.introduced(introduced.getOutput())
					.discontinued(discontinued.getOutput())
					.company(company.getOutput()).create();
			computerService.insert(computer);
			request.setAttribute("success",
					"Computer " + computerName.getOutput() + " added");
		} else {
			resetParameters(request);
			request.setAttribute("danger", "Error: Check red labels!");
		}

		doGet(request, response);
	}

	private void resetParameters(HttpServletRequest request) {
		request.setAttribute("computerName",
				request.getParameter("computerName"));
		request.setAttribute("introduced", request.getParameter("introduced"));
		request.setAttribute("discontinued",
				request.getParameter("discontinued"));
		request.setAttribute("companyId", request.getParameter("companyId"));
	}
}
