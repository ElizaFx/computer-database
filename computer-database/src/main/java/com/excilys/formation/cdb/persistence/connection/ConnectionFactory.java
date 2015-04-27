package com.excilys.formation.cdb.persistence.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.exception.DAOException;
import com.jolbox.bonecp.BoneCP;

public class ConnectionFactory {
	private static final BoneCP connectionPool;
	private static final String properties = "/config.properties";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionFactory.class);
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			try {
				return connectionPool.getConnection();
			} catch (SQLException e) {
				LOGGER.error(
						"Error in ConnectionFactory while getting new connection",
						e);
				throw new DAOException(e);
			}
		}
	};
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.error(
					"Error in ConnectionFactory while getting JDBC driver", e);
			throw new DAOException(e);
		}
		try {
			ConnectionProperties conf = new ConnectionProperties(properties);
			connectionPool = new BoneCP(conf);
		} catch (SQLException e) {
			LOGGER.error(
					"Error in ConnectionFactory while building connection pool",
					e);
			throw new DAOException(e);
		}
	}

	/**
	 * @return new connection
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	public static Connection getConnection() {
		return threadLocal.get();
	}

	/**
	 * @return new connection with not auto commit
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	public static Connection createTransactionConnection() {
		try {
			Connection c = getConnection();
			c.setAutoCommit(false);
			LOGGER.info("new Transation Connection");
			return c;
		} catch (SQLException e) {
			LOGGER.error(
					"Error in ConnectionFactory.createTransactionConnection()",
					e);
			throw new DAOException(e);
		}
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
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory.rollback()", e);
			throw new DAOException(e);
		}
	}

	public static void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory.commit()", e);
			throw new DAOException(e);
		}
	}

	public static boolean isAutoCommit() {
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
	public static void closeConnection(Statement s, ResultSet r) {
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
				threadLocal.remove();
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
	public static void closeTransactionConnection() {
		try {
			Connection c = getConnection();
			if ((c != null) && !c.isClosed()) {
				c.close();
				threadLocal.remove();
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ConnectionFactory", e);
			throw new DAOException(e);
		}
		LOGGER.info("Transation Connection closed");
	}
}
