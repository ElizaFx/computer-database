package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.util.Util;
import com.excilys.formation.cdb.validation.CompanyValidator;
import com.excilys.formation.cdb.validation.ComputerValidator;
import com.excilys.formation.cdb.validation.DateValidator;
import com.excilys.formation.cdb.validation.NameValidator;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if ((id != null) && Util.isNumeric(id)) {
			Computer computer = ComputerService.INSTANCE.find(Long
					.parseLong(id));
			if (computer != null) {
				request.setAttribute("computer",
						ComputerMapper.computerModelToDTO(computer));
			} else {
				request.setAttribute("danger",
						"Error: This computer does't exist!");
			}
		}
		request.setAttribute("lCompanies", CompanyMapper
				.companyModelToDTO(CompanyService.INSTANCE.findAll()));
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);
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
		ComputerValidator computer = new ComputerValidator(
				request.getParameter("id"));
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
			request.setAttribute("discontinuedClass", "has-error");
			request.setAttribute("discontinuedMessage", discontinued.getMsg());
			hasError = true;
		}
		if (!company.isValid()) {
			request.setAttribute("companyClass", "has-error");
			request.setAttribute("companyMessage", company.getMsg());
			hasError = true;
		}
		if (!hasError) {
			computer.getOutput().setName(computerName.getOutput());
			computer.getOutput().setIntroduced(introduced.getOutput());
			computer.getOutput().setDiscontinued(discontinued.getOutput());
			computer.getOutput().setCompany(company.getOutput());
			ComputerService.INSTANCE.update(computer.getOutput());
			request.setAttribute("success",
					"Computer " + computerName.getOutput() + " edited");
		} else {
			resetParameters(request);
			request.setAttribute("danger", "Error: Check red labels!");
		}
		if (computer.isValid()) {
			request.setAttribute("computer",
					ComputerMapper.computerModelToDTO(computer.getOutput()));
		} else {
			request.setAttribute("danger", "Error: This computer does't exist!");
		}
		request.setAttribute("lCompanies", CompanyMapper
				.companyModelToDTO(CompanyService.INSTANCE.findAll()));
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);
	}

	private void resetParameters(HttpServletRequest request) {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("computerName",
				request.getParameter("computerName"));
		request.setAttribute("introduced", request.getParameter("introduced"));
		request.setAttribute("discontinued",
				request.getParameter("discontinued"));
		request.setAttribute("companyId", request.getParameter("companyId"));
	}
}
