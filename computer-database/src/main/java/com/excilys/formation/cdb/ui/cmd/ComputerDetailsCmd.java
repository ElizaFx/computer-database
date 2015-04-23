package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;

public class ComputerDetailsCmd implements ICommand {

	private long id;

	public ComputerDetailsCmd(long id) {
		this.id = id;
	}

	@Override
	public void execute() {
		Computer computer = ComputerService.INSTANCE.find(id);

		if (computer == null) {
			System.out.println("Computer not found");
			return;
		}

		System.out.println("The choosen computer is : " + computer.getName());
		System.out.println("Its id is : " + computer.getId());
		if (computer.getCompany() != null) {
			System.out.println("The vendor is : " + computer.getCompany());
		} else {
			System.out.println("Vendor unknown");
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
