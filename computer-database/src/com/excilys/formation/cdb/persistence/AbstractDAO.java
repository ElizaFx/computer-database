package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Joxit
 *
 * @param <T>
 */
public abstract class AbstractDAO<T> {

	private final Class<T> modelClass;

	private static final String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String user = "admincdb";
	private static final String password = "qwerty1234";
	private static Connection c = null;

	/**
	 * @param modelClass
	 */
	public AbstractDAO(Class<T> modelClass) {
		this.modelClass = modelClass;
	}

	/**
	 * @return connection or new connection if does not exist
	 * @throws SQLException
	 *             if a database access error occurs or the url is null
	 */
	private static Connection getConnection() throws SQLException {
		if (c == null) {
			c = DriverManager.getConnection(url, user, password);
		}
		return c;
	}

	/**
	 * @return new statement
	 * @throws SQLException
	 *             if a database access error occurs or this method is called on
	 *             a closed connection
	 */
	protected static Statement createStatement() throws SQLException {
		return getConnection().createStatement();
	}

	/**
	 * @return all row of the table of T
	 */
	public List<T> findAll() {
		List<T> res = new ArrayList<>();
		Statement statement = null;
		try {
			statement = createStatement();
			ResultSet result = statement.executeQuery("select * from "
					+ modelClass.getSimpleName().toLowerCase());
			while (result.next()) {
				res.add(getModel(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
			}
		}
		return res;
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public T find(Predicate<? super T> predicate) {
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 * 
	 * @param model
	 * @return
	 */
	public abstract int insert(T model);

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 * 
	 * @param model
	 */
	public abstract int remove(T model);

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 * 
	 * @param model
	 */
	public abstract int update(T model);

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	public abstract T find(Object id);

	/**
	 * @param result
	 * @return result translated in the model
	 */
	protected abstract T getModel(ResultSet result);

	/**
	 * @param request
	 *            in the form column1=data1, culumn2=data2...
	 */
	protected int insertRequest(String request) {
		int res = 0;
		Statement statement = null;
		try {
			statement = createStatement();
			res = statement.executeUpdate("insert into `"
					+ modelClass.getSimpleName().toLowerCase() + "` set "
					+ request);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
			}
		}
		return res;
	}

	/**
	 * @param id
	 *            in the form id=dataId
	 * @param request
	 *            in the form column1=data1, culumn2=data2...
	 */
	protected int updateRequest(String id, String request) {
		int res = 0;
		Statement statement = null;
		try {
			statement = createStatement();
			res = statement.executeUpdate("update `"
					+ modelClass.getSimpleName().toLowerCase() + "` set "
					+ request + " where " + id + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
			}
		}
		return res;
	}

	/**
	 * @param request
	 *            in the form column1=data1 and culumn2=data2...
	 */
	protected int removeRequest(String request) {
		Statement statement = null;
		int res = 0;
		try {
			statement = createStatement();
			res = statement.executeUpdate("delete from `"
					+ modelClass.getSimpleName().toLowerCase() + "` where "
					+ request + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
			}
		}
		return res;
	}
}
