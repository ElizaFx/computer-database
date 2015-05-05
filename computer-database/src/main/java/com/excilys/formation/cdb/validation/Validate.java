package com.excilys.formation.cdb.validation;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class Validate<T> {
	private final String input;
	private String msg;
	private int validity = 0;

	public Validate(String input) {
		this.input = input != null ? input.trim() : null;
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public boolean hasInput() {
		return (input != null) && !input.isEmpty();
	}

	public abstract T getOutput();

	public String getMsg() {
		return msg;
	}

	protected void setValidMsg(String msg) {
		this.msg = msg;
		toggleValid();
	}

	protected void setWarningMsg(String msg) {
		this.msg = msg;
		toggleWarning();
	}

	protected void setErrorMsg(String msg) {
		this.msg = msg;
		toggleError();
	}

	public boolean isValid() {
		return validity == 0;
	}

	public boolean isError() {
		return validity == 1;
	}

	public boolean isWarning() {
		return validity == 2;
	}

	public void toggleValid() {
		validity = 0;
	}

	public void toggleError() {
		validity = 1;
	}

	public void toggleWarning() {
		validity = 2;
	}

	public String getInput() {
		return input;
	}

}
