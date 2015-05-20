package com.excilys.formation.cdb.ui.requests;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.ui.CLI;
import com.excilys.formation.cdb.ui.cmd.CreateComputerCmd;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.util.Util;

public class MKRequest implements IRequest {
	private ICompanyService companyService = (ICompanyService) CLI.context
			.getBean("companyService");
	private final List<String> request;

	public final static String CMD = "mk";

	public static final String NAME = "-n";
	public static final String INTRODUCED = "-i";
	public static final String DISCONTINUED = "-d";
	public static final String COMPANY_ID = "-c";

	private static final Set<String> MK_ARGS = new HashSet<>();
	static {
		MK_ARGS.add(NAME);
		MK_ARGS.add(INTRODUCED);
		MK_ARGS.add(DISCONTINUED);
		MK_ARGS.add(COMPANY_ID);
	}

	public MKRequest(List<String> request) {
		this.request = request;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company = null;
		for (int i = 0; i < (request.size() - 1); i++) {
			if (MK_ARGS.contains(request.get(i))) {
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
							Long companyId = Long.parseLong(request.get(i + 1));
							company = companyService.find(companyId);
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
		if (name == null) {
			throw new RequestNotFoundException("CREATION ERROR name not found");
		}
		return new CreateComputerCmd(name, introduced, discontinued, company);
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println(CMD + " [" + NAME + " <name>] [" + INTRODUCED
				+ " <date>] [" + DISCONTINUED + " <date>] [" + COMPANY_ID
				+ " <company id>]");

		System.out
				.println("    "
						+ NAME
						+ " <name> : name of the computer (You can yse \" for names with spaces) REQUIERED");
		System.out
				.println("    "
						+ INTRODUCED
						+ " <date> : date when it was introduced (iso date YYYY-MM-JJ or dd-MM-yyyy)");
		System.out
				.println("    "
						+ DISCONTINUED
						+ " <date> : date when it was discontinued (iso date YYYY-MM-JJ or dd-MM-yyyy)");
		System.out.println("    " + COMPANY_ID
				+ " <company id> : id of the company, must be in the DataBase");
	}
}
