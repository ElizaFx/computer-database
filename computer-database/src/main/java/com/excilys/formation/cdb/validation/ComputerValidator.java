package com.excilys.formation.cdb.validation;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.util.Util;

public class ComputerValidator extends Validate<Computer> {
	private Computer computer;

	public ComputerValidator(String input) {
		super(input);
		if (hasInput() && Util.isNumeric(getInput())) {
			computer = ComputerService.INSTANCE
					.find(Long.parseLong(getInput()));
		}
		if (computer == null) {
			setErrorMsg("This computer does't exist");
		}
	}

	@Override
	public Computer getOutput() {
		return computer;
	}

}
