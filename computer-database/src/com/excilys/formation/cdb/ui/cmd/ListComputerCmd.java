package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class ListComputerCmd extends ListCommand<Computer> {

	public ListComputerCmd() {
		super(ComputerDAO.getInstance());
	}

	@Override
	public String format(Computer entity) {
		return entity.toString();
	}

}
