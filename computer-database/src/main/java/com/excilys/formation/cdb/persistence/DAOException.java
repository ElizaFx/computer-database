package com.excilys.formation.cdb.persistence;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -66907293716901067L;

	public DAOException(Exception e) {
		super(e);
	}
}
