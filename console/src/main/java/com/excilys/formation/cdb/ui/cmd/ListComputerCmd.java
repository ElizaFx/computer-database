package com.excilys.formation.cdb.ui.cmd;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.util.WebServiceUtils;

public class ListComputerCmd implements ICommand {
	@Override
	public void execute() {
		Response response = WebServiceUtils.getFindAllComputerResponse();
		System.out.println("Début de la liste des elements :");
		if (response.getStatus() == 200) {
			Arrays.stream(response.readEntity(ComputerDTO[].class)).forEach(
					c -> System.out.println(format(c)));
		} else {
			System.out.println("Empty. Status: " + response.getStatus());
		}
		System.out.println("Fin de la liste des elements");
	}

	public String format(ComputerDTO entity) {
		return String.format("%4d : %s", entity.getId(), entity.getName());
	}

}
