package com.excilys.formation.cdb.exception;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -66907293716901067L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Exception e) {
		super(e);
	}
}
