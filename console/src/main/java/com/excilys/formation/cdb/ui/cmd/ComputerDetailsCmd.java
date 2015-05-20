package com.excilys.formation.cdb.ui.cmd;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.ui.CLI;

public class ComputerDetailsCmd implements ICommand {

	private IComputerService computerService = CLI.context.getBean(
			"computerService", IComputerService.class);
	private long id;

	public ComputerDetailsCmd(long id) {
		this.id = id;
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void execute() {
		Computer computer = computerService.find(id);

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
