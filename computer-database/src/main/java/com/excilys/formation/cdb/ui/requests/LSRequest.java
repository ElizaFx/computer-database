package com.excilys.formation.cdb.ui.requests;

import java.util.HashSet;
import java.util.Set;

import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.ui.cmd.ListCompanyCmd;
import com.excilys.formation.cdb.ui.cmd.ListComputerCmd;

public class LSRequest implements IRequest {

	public final static String CMD = "ls";
	private final static Set<String> LIST_ARGS = new HashSet<String>();
	private final static String LIST_COMPUTER = "computer";
	private final static String LIST_COMPANIES = "companies";
	static {
		LIST_ARGS.add(LIST_COMPANIES);
		LIST_ARGS.add(LIST_COMPUTER);
	}

	private final String arg;

	public LSRequest(String arg) {
		this.arg = arg;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		ICommand command = null;
		if (!LIST_ARGS.contains(arg)) {
			throw new RequestNotFoundException(arg
					+ " is not a valid argument for " + CMD);
		}
		switch (arg) {
			case LIST_COMPANIES:
				command = new ListCompanyCmd();
				break;
			case LIST_COMPUTER:
				command = new ListComputerCmd();
				break;
		}
		return command;
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println(CMD + " [" + LIST_COMPUTER + "|" + LIST_COMPANIES
				+ "]");
		System.out.println("    " + LIST_COMPUTER + " : list all computers");
		System.out.println("    " + LIST_COMPANIES + " : list all companies");
	}

}
