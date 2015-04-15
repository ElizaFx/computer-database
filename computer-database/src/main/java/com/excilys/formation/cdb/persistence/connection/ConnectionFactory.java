package com.excilys.formation.cdb.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.formation.cdb.persistence.DAOException;

public class ConnectionFactory {

	private static final String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String user = "admincdb";
	private static final String password = "qwerty1234";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @return connection or new connection if does not exist
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
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
	public static void closeConnection(Connection c, Statement s, ResultSet r) {
		try {
			if ((r != null) && !r.isClosed()) {
				r.close();
			}
			if ((s != null) && !s.isClosed()) {
				s.close();
			}
			if ((c != null) && !c.isClosed()) {
				c.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
