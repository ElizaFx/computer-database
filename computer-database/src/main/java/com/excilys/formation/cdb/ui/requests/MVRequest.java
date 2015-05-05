package com.excilys.formation.cdb.ui.requests;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.CLI;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.ui.cmd.UpdateComputerCmd;
import com.excilys.formation.cdb.util.Util;

public class MVRequest implements IRequest {
	private ComputerService computerService = (ComputerService) CLI.context
			.getBean("computerService");
	private CompanyService companyService = (CompanyService) CLI.context
			.getBean("companyService");
	private final List<String> request;

	public final static String CMD = "mv";

	public static final String NAME = "-n";
	public static final String INTRODUCED = "-i";
	public static final String DISCONTINUED = "-d";
	public static final String COMPANY_ID = "-c";

	public static final Set<String> MV_ARGS = new HashSet<>();
	static {
		MV_ARGS.add(NAME);
		MV_ARGS.add(INTRODUCED);
		MV_ARGS.add(DISCONTINUED);
		MV_ARGS.add(COMPANY_ID);
	}

	public MVRequest(List<String> request) {
		this.request = request;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		boolean hasChanges = false;
		for (int i = 1; i < (request.size() - 1); i++) {
			if (i == 1) {
				if (Util.isNumeric(request.get(i))) {
					id = Long.parseLong(request.get(i));
				} else {
					throw new RequestNotFoundException("id malformed");
				}
			} else if (MV_ARGS.contains(request.get(i))) {
				switch (request.get(i)) {
					case NAME: {
						name = request.get(i + 1);
						break;
					}
					case INTRODUCED: {
						introduced = Util.parseDate(request.get(i + 1));
						if (introduced == null) {
							throw new RequestNotFoundException(
									"Introduced date malformed!");
						}
						break;
					}
					case DISCONTINUED: {
						discontinued = Util.parseDate(request.get(i + 1));
						if (discontinued == null) {
							throw new RequestNotFoundException(
									"Discontinued date malformed!");
						}
						break;
					}
					case COMPANY_ID: {
						if (Util.isNumeric(request.get(i + 1))) {
							companyId = Long.parseLong(request.get(i + 1));
						} else {
							throw new RequestNotFoundException(
									"Company id malformed");
						}
						break;
					}
				}
				i++;
			}

		}
		if (id == null) {
			throw new RequestNotFoundException("UPDATE ERROR id not found");
		}
		Computer computer = computerService.find(id);
		if (computer == null) {
			throw new RequestNotFoundException(
					"UPDATE ERROR no computer for this id");
		}
		if (name != null) {
			computer.setName(name);
			hasChanges = true;
		}
		if (introduced != null) {
			computer.setIntroduced(introduced);
			hasChanges = true;
		}
		if (discontinued != null) {
			computer.setDiscontinued(discontinued);
			hasChanges = true;
		}
		if ((companyId != null) && (companyId != 0)) {
			computer.setCompany(companyService.find(companyId));
			hasChanges = true;
		}
		if (hasChanges) {
			return new UpdateComputerCmd(computer);
		} else {
			throw new RequestNotFoundException("No updates to do");
		}
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println(CMD + " <computer id> [" + NAME + " <name>] ["
				+ INTRODUCED + " <date>] [" + DISCONTINUED + " <iso date>] ["
				+ COMPANY_ID + " <company id>]");
		System.out.println("    <computer id> : current id of the computer");
		System.out
				.println("    "
						+ NAME
						+ " <name> : name of the computer (You can yse \" for names with spaces) REQUIERED");
		System.out
				.println("    "
						+ INTRODUCED
						+ " <date> : date when it was introduced (iso date yyyy-MM-dd or dd-MM-yyyy)");
		System.out
				.println("    "
						+ DISCONTINUED
						+ " <iso date> : date when it was discontinued (iso date yyyy-MM-dd or dd-MM-yyyy)");
		System.out.println("    " + COMPANY_ID
				+ " <company id> : id of the company, must be in the DataBase");
	}

}
