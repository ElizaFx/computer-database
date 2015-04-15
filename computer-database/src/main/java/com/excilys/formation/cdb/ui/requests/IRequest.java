package com.excilys.formation.cdb.ui.requests;

import com.excilys.formation.cdb.ui.cmd.ICommand;

/**
 * Interface for process request.
 * 
 * @author joxit
 *
 */
public interface IRequest {
	/**
	 * 
	 * @return the right command for a request
	 * @throws RequestNotFoundException
	 *             if the process goes wrong
	 */
	public ICommand processCommand() throws RequestNotFoundException;
}
