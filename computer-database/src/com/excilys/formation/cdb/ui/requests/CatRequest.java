package com.excilys.formation.cdb.ui.requests;

import com.excilys.formation.cdb.ui.cmd.ComputerDetailsCmd;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.util.Util;

public class CatRequest implements IRequest {

	public final static String CMD = "cat";

	private String arg;

	public CatRequest(String arg) {
		this.arg = arg;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		long id = 0;
		if (Util.isNumeric(arg)) {
			id = Long.parseLong(arg);
		} else {
			throw new RequestNotFoundException(arg
					+ " is not a valid long for " + CMD);
		}
		return new ComputerDetailsCmd(id);
	}

	public static void help() {
		System.out.println(CMD + " <computer id>");
	}

}
