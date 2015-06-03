package com.excilys.formation.cdb.service.impl;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.model.User;
import com.excilys.formation.cdb.persistence.IUserDAO;
import com.excilys.formation.cdb.service.IUserService;

@Service
public class UserService implements IUserService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);

	@Autowired
	private IUserDAO userDAO;

	protected final MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDAO.find(username);

		if (user == null) {
			LOGGER.debug("Query returned no results for user '" + username
					+ "'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"));
		}

		if (user.getAuthorities().size() == 0) {
			LOGGER.debug("User '" + username
					+ "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"));
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.isEnabled(), true,
				true, true, user.getAuthorities().stream()
						.map(a -> new SimpleGrantedAuthority(a.getAuthority()))
						.collect(Collectors.toList()));
	}

}
