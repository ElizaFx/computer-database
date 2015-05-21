package com.excilys.formation.cdb.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.User;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User find(String username) {
		return em.find(User.class, username);
	}

}
