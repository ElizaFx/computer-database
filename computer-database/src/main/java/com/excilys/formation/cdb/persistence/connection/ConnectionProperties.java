package com.excilys.formation.cdb.persistence.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.excilys.formation.cdb.persistence.DAOException;

public class ConnectionProperties {
	private final String url;
	private final String user;
	private final String password;

	public ConnectionProperties(String path) {
		Properties prop = new Properties();
		String propFileName = "config.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new DAOException("property file '" + propFileName
						+ "' not found in the classpath");
			}
		} catch (IOException e) {
			throw new DAOException("error when loading '" + propFileName);
		}

		// get the property value and print it out
		user = prop.getProperty("user");
		url = prop.getProperty("url");
		password = prop.getProperty("password");

	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
