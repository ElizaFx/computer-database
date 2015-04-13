package com.excilys.formation.cdb.ui.requests;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.excilys.formation.cdb.ui.cmd.CreateComputerCmd;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.util.Util;

public class MKRequest implements IRequest {
	private final List<String> request;

	public final static String CMD = "mk";

	private static final String NAME = "-n";
	private static final String INTRODUCED = "-i";
	private static final String DISCONTINUED = "-d";
	private static final String COMPANY_ID = "-c";

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
		Date introduced = null;
		Date discontinued = null;
		Long companyId = null;
		for (int i = 0; i < (request.size() - 1); i++) {
			if (MK_ARGS.contains(request.get(i))) {
				System.out.println(request.get(i));
				switch (request.get(i)) {
					case NAME: {
						name = request.get(i + 1);
						break;
					}
					case INTRODUCED: {
						try {
							introduced = DateFormat.getInstance().parse(
									request.get(i + 1));
						} catch (ParseException e) {
							throw new RequestNotFoundException(
									"Introduced date malformed");
						}
						break;
					}
					case DISCONTINUED: {
						try {
							discontinued = DateFormat.getInstance().parse(
									request.get(i + 1));
						} catch (ParseException e) {
							throw new RequestNotFoundException(
									"Discontinued date malformed");
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
		if (name == null) {
			throw new RequestNotFoundException("CREATION ERROR name not found");
		}
		System.out.printf("%s %s %s %s\n", name, introduced, discontinued,
				companyId);
		return new CreateComputerCmd(name, introduced, discontinued, companyId);
	}

	public static void help() {
		System.out.println(CMD + " [" + NAME + " <name>] [" + INTRODUCED
				+ " <iso date>] [" + DISCONTINUED + " <iso date>] ["
				+ COMPANY_ID + " <company id>]");

		System.out
				.println("    "
						+ NAME
						+ " <name> : name of the computer (You can yse \" for names with spaces) REQUIERED");
		System.out
				.println("    "
						+ INTRODUCED
						+ " <iso date> : date when it was introduced (iso date YYYY-MM-JJ)");
		System.out
				.println("    "
						+ DISCONTINUED
						+ " <iso date> : date when it was discontinued (iso date YYYY-MM-JJ)");
		System.out.println("    " + COMPANY_ID
				+ " <company id> : id of the company, must be in the DataBase");
	}
}
