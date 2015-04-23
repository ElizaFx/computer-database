package com.excilys.formation.cdb.ui.requests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.ui.cmd.ICommand;

public class Request implements IRequest {

	public final static String HELP = "help";

	public final static Set<String> FIRST_ARGS = new HashSet<String>();

	static {
		FIRST_ARGS.add(LSRequest.CMD);
		FIRST_ARGS.add(MKRequest.CMD);
		FIRST_ARGS.add(MVRequest.CMD);
		FIRST_ARGS.add(RMRequest.CMD);
		FIRST_ARGS.add(CatRequest.CMD);
	}

	private final List<String> request;

	public Request(String request) {
		this.request = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
		Matcher regexMatcher = regex.matcher(request);
		while (regexMatcher.find()) {
			this.request.add(regexMatcher.group().replace("\"", ""));
		}
	}

	@Override
	public ICommand processCommand() throws RequestNotFoundException {
		IRequest request = null;
		if (getFirstCmd() == null) {
			throw new RequestNotFoundException("Command is null");
		} else if (!FIRST_ARGS.contains(getFirstCmd())) {
			throw new RequestNotFoundException(getFirstCmd()
					+ " is not a valid command");
		}
		switch (getFirstCmd()) {
			case LSRequest.CMD: {
				if (getSecondCmd() == null) {
					throw new RequestNotFoundException(getFirstCmd()
							+ " need a second arg!");
				} else {
					request = new LSRequest(getSecondCmd());
				}
				break;
			}
			case MKRequest.CMD: {
				request = new MKRequest(this.request);
				break;
			}
			case MVRequest.CMD: {
				if (getSecondCmd() == null) {
					throw new RequestNotFoundException(getFirstCmd()
							+ " need a second arg!");
				} else {
					request = new MVRequest(this.request);
				}
				break;
			}
			case RMRequest.CMD: {
				if (getThirdCmd() == null) {
					throw new RequestNotFoundException(getFirstCmd()
							+ " need a second arg!");
				} else {
					request = new RMRequest(getSecondCmd(), getThirdCmd());
				}
				break;
			}
			case CatRequest.CMD: {
				if (getSecondCmd() == null) {
					throw new RequestNotFoundException(getFirstCmd()
							+ " need a second arg!");
				}
				request = new CatRequest(getSecondCmd());
				break;
			}
			case HELP: {
				help();
				break;
			}

		}
		if (request != null) {
			return request.processCommand();
		} else {
			throw new RequestNotFoundException(getFirstCmd()
					+ " is not a valid command");
		}
	}

	public String getFirstCmd() {
		if (request.size() > 0) {
			return request.get(0);
		} else {
			return null;
		}
	}

	public String getSecondCmd() {
		if (request.size() > 1) {
			return request.get(1);
		} else {
			return null;
		}
	}

	public String getThirdCmd() {
		if (request.size() > 2) {
			return request.get(2);
		} else {
			return null;
		}
	}

	/**
	 * Print help message on stdout
	 */
	public static void help() {
		System.out.println("List of valid commands : ");
		LSRequest.help();
		MKRequest.help();
		MVRequest.help();
		RMRequest.help();
		CatRequest.help();

	}
}
