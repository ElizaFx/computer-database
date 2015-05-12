package com.excilys.formation.cdb.validation;

public class NameValidator extends Validate<String> {

	public NameValidator(String input) {
		super(input);
		if ((getInput() == null) || getInput().isEmpty()) {
			setErrorMsg("error.empty");
		}
	}

	@Override
	public String getOutput() {
		return getInput();
	}

}
