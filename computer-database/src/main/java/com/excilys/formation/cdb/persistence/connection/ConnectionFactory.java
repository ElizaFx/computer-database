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
import org.springframework.test.context.ContextConfiguration;

import com.excilys.formation.cdb.exception.DAOException;

@Component
@ContextConfiguration("/applicationContext.xml")
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
	 * @return new connection with not auto commit
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	public Connection createTransactionConnection() {

		LOGGER.info("new Transation Connection");
		return DataSourceUtils.getConnection(dataSource);

	}

	/**
	 * Undoes all changes made in the current transaction and releases any
	 * database locks currently held by this Connection object. This method
	 * should be used only when auto-commit mode has been disabled
	 * 
	 * @param c
	 *            connection
	 * @throws DAOException
	 *             if a database access error occurs, this method is called
	 *             while participating in a distributed transaction, this method
	 *             is called on a closed connection or this Connection object is
	 *             in auto-commit mode
	 */
	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory.rollback()", e);
			throw new DAOException(e);
		}
	}

	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory.commit()", e);
			throw new DAOException(e);
		}
	}

	public boolean isAutoCommit() {
		try {
			return getConnection().getAutoCommit();
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory.isAutoCommit()", e);
			throw new DAOException(e);
		}
	}

	/**
	 * @param c
	 *            connection to close or null
	 * @param s
	 *            statement to close or null
	 * @param r
	 *            result set to close or null
	 */
	public void closeConnection(Statement s, ResultSet r) {
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
			Connection c = getConnection();
			if ((c != null) && !c.isClosed() && isAutoCommit()) {
				c.close();
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory while closing connection",
					e);
			throw new DAOException(e);
		}
	}

	/**
	 * @param c
	 *            connection to close or null
	 * @param s
	 *            statement to close or null
	 * @param r
	 *            result set to close or null
	 */
	public void closeTransactionConnection() {
		try {
			Connection c = getConnection();
			if ((c != null) && !c.isClosed()) {
				c.close();
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory", e);
			throw new DAOException(e);
		}
		LOGGER.info("Transation Connection closed");
	}
}
