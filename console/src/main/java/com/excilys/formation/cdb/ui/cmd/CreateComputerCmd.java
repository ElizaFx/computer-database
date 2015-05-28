package com.excilys.formation.cdb.ui.cmd;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.ui.CLI;
import com.excilys.formation.cdb.util.WebServiceUtils;

public class CreateComputerCmd implements ICommand {

	@Autowired
	private ComputerMapper computerMapper = CLI.context.getBean(
			"computerMapper", ComputerMapper.class);
	private final ComputerDTO computer;

	public CreateComputerCmd(ComputerDTO computer) {
		this.computer = computer;
	}

	public CreateComputerCmd(String name, String introduced,
			String discontinued, CompanyDTO company) {
		this(new ComputerDTO(null, name, introduced, discontinued,
				company != null ? company.getId() : null,
				company != null ? company.getName() : null));
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Create failed : Computer is null");
			return;
		}
		Response response = WebServiceUtils.postComputerResponse(computer,
				MediaType.APPLICATION_JSON_TYPE);
		if (response.getStatus() == 200) {
			System.out.println("Entry insered."
					+ response.readEntity(ComputerDTO.class));
		} else {
			System.out.println("Sorry, something goes wrong. Status: "
					+ response.getStatus());
		}
	}
}