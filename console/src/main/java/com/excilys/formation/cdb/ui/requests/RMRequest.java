package com.excilys.formation.cdb.ui.requests;

import java.util.HashSet;
import java.util.Set;

import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.ui.cmd.DeleteCompanyCmd;
import com.excilys.formation.cdb.ui.cmd.DeleteComputerCmd;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.util.Util;

public class RMRequest implements IRequest {

	public final static String CMD = "rm";
	public final static Set<String> RM_ARGS = new HashSet<String>();
	public final static String RM_COMPUTER = "computer";
	public final static String RM_COMPANIES = "company";
	static {
		RM_ARGS.add(RM_COMPANIES);
		RM_ARGS.add(RM_COMPUTER);
	}

	private final String arg;
	private final String sID;

	public RMRequest(String arg, String sID) {
		this.arg = arg;
		this.sID = sID;
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		if (!RM_ARGS.contains(arg)) {
			throw new RequestNotFoundException(CMD + " incorrect second arg! ");
		}
		long id = 0;
		if (Util.isNumeric(sID)) {
			id = Long.parseLong(sID);
		} else {
			throw new RequestNotFoundException(sID
					+ " is not a valid long for " + CMD);
		}
		System.out.println(id);
		switch (arg) {
			case RM_COMPUTER: {
				return new DeleteComputerCmd(id);
			}
			case RM_COMPANIES: {
				return new DeleteCompanyCmd(id);
			}
			default: {
				return null;
			}
		}
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println(CMD + " <computer id>");
		System.out.println(CMD + " [" + RM_COMPUTER + "|" + RM_COMPANIES
				+ "] <id>");
		System.out.println("    " + RM_COMPUTER + " : remove a computers");
		System.out.println("    " + RM_COMPANIES + " : remove a company");
	}

}
