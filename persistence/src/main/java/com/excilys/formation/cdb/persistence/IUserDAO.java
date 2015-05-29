package com.excilys.formation.cdb.persistence;

import com.excilys.formation.cdb.model.User;

public interface IUserDAO {
	/**
	 * 
	 * @param username
	 * @return find the user with this username
	 */
	public User find(String username);
}
