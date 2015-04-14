package com.excilys.formation.cdb.ui.requests;

import com.excilys.formation.cdb.ui.cmd.DeleteComputerCmd;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.util.Util;

public class RMRequest implements IRequest {

	public final static String CMD = "rm";

	private final String arg;

	public RMRequest(String arg) {
		this.arg = arg;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		if (arg == null) {
			throw new RequestNotFoundException(CMD + " need a second arg!");
		}
		long id = 0;
		if (Util.isNumeric(arg)) {
			id = Long.parseLong(arg);
		} else {
			throw new RequestNotFoundException(arg
					+ " is not a valid long for " + CMD);
		}
		System.out.println(id);
		return new DeleteComputerCmd(id);
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println(CMD + " <computer id>");
	}

}
