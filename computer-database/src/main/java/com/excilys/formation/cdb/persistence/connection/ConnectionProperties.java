package com.excilys.formation.cdb.persistence.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.util.Util;
import com.jolbox.bonecp.BoneCPConfig;

public class ConnectionProperties extends BoneCPConfig {

	private static final long serialVersionUID = -8001459556204208609L;

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

		setUsername(prop.getProperty("user"));
		setJdbcUrl(prop.getProperty("url"));
		setPassword(prop.getProperty("password"));

		String minConnectionsPerPartition = prop
				.getProperty("minConnectionsPerPartition");
		String maxConnectionsPerPartition = prop
				.getProperty("maxConnectionsPerPartition");
		String partitionCount = prop.getProperty("partitionCount");
		if (Util.isNumeric(minConnectionsPerPartition)) {
			setMinConnectionsPerPartition(Integer
					.parseInt(minConnectionsPerPartition));
		} else {
			setMinConnectionsPerPartition(5);
		}
		if (Util.isNumeric(maxConnectionsPerPartition)) {
			setMaxConnectionsPerPartition(Integer
					.parseInt(maxConnectionsPerPartition));
		} else {
			setMaxConnectionsPerPartition(10);
		}

		if (Util.isNumeric(partitionCount)) {
			setPartitionCount(Integer.parseInt(partitionCount));
		} else {
			setPartitionCount(1);
		}
		super.setLogStatementsEnabled(false);
	}
}
