package com.excilys.formation.cdb.ui.cmd;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.ui.CLI;

public class ListCompanyCmd implements ICommand {
	@Override
	public void execute() {
		Response response = CLI.getWebTarget().path("company/findAll")
				.request().get();
		System.out.println("Début de la liste des elements :");
		if (response.getStatus() == 200) {
			Arrays.stream(response.readEntity(CompanyDTO[].class)).forEach(
					c -> System.out.println(format(c)));
		} else {
			System.out.println("Empty. Status: " + response.getStatus());
		}
		System.out.println("Fin de la liste des elements");
	}

	public String format(CompanyDTO entity) {
		return String.format("%3d : %s", entity.getId(), entity.getName());
	}
}
