package com.excilys.formation.cdb.ui.requests;

import com.excilys.formation.cdb.ui.cmd.ICommand;

public interface IRequest {
	public ICommand processCommand() throws RequestNotFoundException;
}
