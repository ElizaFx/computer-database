package com.excilys.formation.cdb.ui.cmd;

import javax.ws.rs.core.Response;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.util.WebServiceUtils;

public class ComputerDetailsCmd implements ICommand {
	private long id;

	public ComputerDetailsCmd(long id) {
		this.id = id;
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void execute() {
		Response response = WebServiceUtils.getWebTarget("computer/find/" + id)
				.get();

		if (response.getStatus() != 200) {
			System.out.println("Computer not found");
			response.close();
			return;
		}
		ComputerDTO computer = response.readEntity(ComputerDTO.class);
		response.close();

		System.out.println("The choosen computer is : " + computer.getName());
		System.out.println("Its id is : " + computer.getId());
		if (computer.getCompanyName() != null) {
			System.out.println("The vendor is : " + computer.getCompanyName());
		} else {
			System.out.println("Vendor unknown");
		}

		if (computer.getIntroduced() != null) {
			System.out.println("It was introduced : "
					+ computer.getIntroduced());
		} else {
			System.out.println("Introduction date unknown");
		}
		if (computer.getDiscontinued() != null) {
			System.out.println("It was discontinued : "
					+ computer.getDiscontinued());
		} else {
			System.out.println("End of life unknown");
		}
	}
}
