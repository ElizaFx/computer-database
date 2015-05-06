package com.excilys.formation.cdb.persistence.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.exception.DAOException;

@Component
public class ConnectionFactory {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionFactory.class);
	@Autowired
	private DriverManagerDataSource dataSource;

	/**
	 * @return new connection
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	/**
	 * @param s
	 *            statement to close or null
	 * @param r
	 *            result set to close or null
	 */
	public void close(Connection c, Statement s, ResultSet r) {
		try {
			if ((r != null) && !r.isClosed()) {
				r.close();
			}
		} catch (SQLException e) {
			LOGGER.error(
					"Error in ConnectionFactory while closing result set : "
							+ r, e);
			throw new DAOException(e);
		}
		try {
			if ((s != null) && !s.isClosed()) {
				s.close();
			}
		} catch (SQLException e) {
			LOGGER.error(
					"Error in ConnectionFactory while closing statement : " + s,
					e);
			throw new DAOException(e);
		}
		try {
			if ((c != null)
					&& !c.isClosed()
					&& !DataSourceUtils
							.isConnectionTransactional(c, dataSource)) {
				DataSourceUtils.doReleaseConnection(c, dataSource);
			}
		} catch (SQLException e) {
			LOGGER.error(
					"Error in ConnectionFactory while closing statement : " + s,
					e);
			throw new DAOException(e);
		}
	}
}
