package com.excilys.formation.cdb.ui.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.util.Util;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final List<CompanyDTO> lCompany = CompanyMapper
			.companyModelToDTO(CompanyService.getInstance().findAll());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if ((id != null) && Util.isNumeric(id)) {
			Computer computer = ComputerService.getInstance().find(
					Long.parseLong(id));
			if (computer != null) {
				request.setAttribute("computer",
						ComputerMapper.computerModelToDTO(computer));
			}
		}
		request.setAttribute("lCompanies", lCompany);
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

		String sComputerName = request.getParameter("computerName");
		String sIntroduced = request.getParameter("introduced");
		String sDiscontinued = request.getParameter("discontinued");
		String sCompanyId = request.getParameter("companyId");
		StringBuilder messageError = new StringBuilder();

		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long companyId = 0;
		String id = request.getParameter("id");
		Computer computer = null;
		if ((id != null) && Util.isNumeric(id)) {
			computer = ComputerService.getInstance().find(Long.parseLong(id));
			if (computer != null) {
				request.setAttribute("computer",
						ComputerMapper.computerModelToDTO(computer));
			}
		}
		if ((sComputerName == null) || sComputerName.trim().isEmpty()) {
			messageError.append("Incorrect Name").append("<br />");
			request.setAttribute("computerNameClass", "has-error");
		} else {
			sComputerName = sComputerName.trim();
		}

		if (Util.isDate(sIntroduced)) {
			introduced = Util.parseDate(sIntroduced);
			if ((introduced.toEpochSecond(ZoneOffset.UTC) < 0l)
					|| (introduced.toEpochSecond(ZoneOffset.UTC) > (Integer.MAX_VALUE * 1000l))) {
				messageError.append("Incorrect introduced date : ")
						.append("range 1970-01-01 to 2038-01-19")
						.append("<br />");
				request.setAttribute("introducedClass", "has-error");
			}
		} else if ((sIntroduced != null) && !sIntroduced.isEmpty()) {
			messageError.append("Incorrect introduced date : ")
					.append("Malformed date").append("<br />");
			request.setAttribute("introducedClass", "has-error");
		}
		if (Util.isDate(sDiscontinued)) {
			discontinued = Util.parseDate(sDiscontinued);
			if ((discontinued.toEpochSecond(ZoneOffset.UTC) < 0)
					|| (discontinued.toEpochSecond(ZoneOffset.UTC) > (Integer.MAX_VALUE * 1000l))) {
				messageError.append("Incorrect discontinued date : ")
						.append("range 1970-01-01 to 2038-01-19")
						.append("<br />");
				request.setAttribute("discontinuedClass", "has-error");
			}
		} else if ((sDiscontinued != null) && !sDiscontinued.isEmpty()) {
			messageError.append("Incorrect discontinued date : ")
					.append("Malformed date").append("<br />");
			request.setAttribute("discontinuedClass", "has-error");
		}
		if (Util.isNumeric(sCompanyId)) {
			companyId = Integer.parseInt(sCompanyId);
			if ((companyId != 0)
					&& (CompanyService.getInstance().find(companyId) == null)) {
				messageError.append("Incorrect company ID : ")
						.append("This company doesn't exist").append("<br />");
				request.setAttribute("companyIdClass", "has-error");
			}
		} else if ((sCompanyId != null) && !sCompanyId.isEmpty()) {
			messageError.append("Incorrect company ID : ")
					.append("Malformed company ID").append("<br />");
			request.setAttribute("companyIdClass", "has-error");
		}
		System.out.println(introduced != null ? introduced
				.toEpochSecond(ZoneOffset.UTC) : "");
		if ((messageError.length() == 0) && (computer != null)) {
			computer.setName(sComputerName);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany(CompanyService.getInstance().find(companyId));
			ComputerService.getInstance().update(computer);
			request.setAttribute("success", "Computer " + sComputerName
					+ " edited");
			if (computer != null) {
				request.setAttribute("computer",
						ComputerMapper.computerModelToDTO(computer));
			}

		} else {
			request.setAttribute("computerName",
					request.getParameter("computerName"));
			request.setAttribute("introduced",
					request.getParameter("introduced"));
			request.setAttribute("discontinued",
					request.getParameter("discontinued"));
			request.setAttribute("companyId", request.getParameter("companyId"));
			request.setAttribute("danger", "Error: Check red labels!<br />"
					+ messageError.toString());
		}

		request.setAttribute("lCompanies", lCompany);
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);
	}

}
