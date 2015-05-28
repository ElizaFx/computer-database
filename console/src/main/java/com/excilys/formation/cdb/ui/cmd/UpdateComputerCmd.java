package com.excilys.formation.cdb.ui.cmd;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.util.WebServiceUtils;

public class UpdateComputerCmd implements ICommand {
	private final ComputerDTO computer;

	public UpdateComputerCmd(ComputerDTO computer) {
		this.computer = computer;
	}

	@Override
	public void execute() {
		if (computer == null) {
			System.out.println("Update failed : Computer is null");
			return;
		}
		Response response = WebServiceUtils.putComputerResponse(computer,
				MediaType.APPLICATION_JSON_TYPE);
		if (response.getStatus() == 200) {
			System.out.println("Entry updated. "
					+ response.readEntity(ComputerDTO.class));
		} else {
			System.out.println("Sorry, something goes wrong. Status: "
					+ response.getStatus());
		}

	}
}
