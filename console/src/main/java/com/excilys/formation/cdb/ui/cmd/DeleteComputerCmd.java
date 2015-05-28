package com.excilys.formation.cdb.ui.cmd;

import javax.ws.rs.core.Response;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.util.WebServiceUtils;

public class DeleteComputerCmd implements ICommand {
	private final Long id;

	public DeleteComputerCmd(Computer computer) {
		id = computer.getId();
	}

	public DeleteComputerCmd(Long id) {
		this.id = id;
	}

	@Override
	public void execute() {
		if (id == null) {
			System.out.println("Delete failed : Computer is null");
			return;
		}
		Response response = WebServiceUtils.deleteComputerResponse(id);
		if (response.getStatus() == 204) {
			System.out.println("Entry removed.");
		} else {
			System.out.println("Sorry, something goes wrong. Status: "
					+ response.getStatus());
		}
	}

}
