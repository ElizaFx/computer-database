package com.excilys.formation.cdb.persistence;

import com.excilys.formation.cdb.model.User;

public interface IUserDAO {
	public User find(String username);
}
