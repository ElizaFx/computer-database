package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class ComputerDetailsCmd implements Command {

	private String name;
	private long id;

	public ComputerDetailsCmd(String name) {
		this.name = name;
	}

	public ComputerDetailsCmd(long id) {
		this.id = id;
	}

	@Override
	public void execute() {
		Computer computer = null;
		if (name != null) {
			computer = ComputerDAO.getInstance().find(
					c -> c.getName().equals(name));
		} else {
			computer = ComputerDAO.getInstance().find(id);
		}
		System.out.println("The choosen computer is : " + computer.getName());
		System.out.println("Its id is : " + computer.getId());
		Company company = CompanyDAO.getInstance()
				.find(computer.getCompanyId());
		if (company != null) {
			System.out.println("The vendor is : " + company.getName());
		} else {
			System.out.println("Vendor unknown");
			/*
			 * throw new NullPointerException("FATAL ERROR: Company id " +
			 * computer.getCompanyId() + " is not valid. Check the database!!");
			 */
		}

		if (computer.getIntroduced() != null) {
			System.out.println("It was introduced : "
					+ computer.getIntroduced());
		} else {
			System.out.println("Introduction date unknown");
		}
		if (computer.getDiscontinued() != null) {
			System.out.println("It was discontinued : "
					+ computer.getDiscontinued());
		} else {
			System.out.println("End of life unknown");
		}
	}
}
