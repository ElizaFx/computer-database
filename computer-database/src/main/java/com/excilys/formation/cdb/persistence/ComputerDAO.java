package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;
import com.excilys.formation.cdb.util.Util;

/**
 *
 * @author Joxit
 */
public enum ComputerDAO implements IComputerDAO {

	_instance;

	@Override
	public Computer getModel(ResultSet result) {
		Computer computer = new Computer();
		try {
			if (!result.isBeforeFirst() || result.next()) {
				computer.setId(result.getLong("computer.id"));
				computer.setName(result.getString("computer.name"));
				computer.setIntroduced(result
						.getTimestamp("computer.introduced"));
				computer.setDiscontinued(result
						.getTimestamp("computer.discontinued"));
				if (result.getLong("company_id") != 0l) {
					computer.setCompany(CompanyDAO.getInstance().getModel(
							result));
				} else {
					computer.setCompany(null);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer;
	}

	@Override
	public Computer find(Object id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Computer res = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("SELECT * FROM computer left outer join company on computer.company_id=company.id WHERE computer.ID=?");
			ps.setObject(1, id);
			result = ps.executeQuery();
			res = getModel(result);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, ps, result);
		}
		return res;
	}

	@Override
	public int insert(Computer model) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("INSERT INTO computer SET NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=?");
			ps.setString(1, model.getName());
			ps.setDate(2, Util.toSqlDate(model.getIntroduced()));
			ps.setDate(3, Util.toSqlDate(model.getDiscontinued()));
			if (model.getCompany() == null) {
				ps.setNull(4, Types.BIGINT);
			} else {
				ps.setLong(4, model.getCompany().getId());
			}
			res = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, ps, null);
		}
		return res;
	}

	@Override
	public int remove(Computer model) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("DELETE FROM computer WHERE ID=?");
			ps.setLong(1, model.getId());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, ps, null);
		}
		return res;

	}

	@Override
	public int update(Computer model) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("UPDATE computer SET NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=? WHERE ID=?");
			ps.setObject(1, model.getName());
			ps.setDate(2, Util.toSqlDate(model.getIntroduced()));
			ps.setDate(3, Util.toSqlDate(model.getDiscontinued()));
			if (model.getCompany() == null) {
				ps.setNull(4, Types.BIGINT);
			} else {
				ps.setLong(4, model.getCompany().getId());
			}
			ps.setLong(5, model.getId());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, ps, null);
		}
		return res;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> res = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT * FROM computer left outer join company on computer.company_id=company.id");
			while (result.next()) {
				res.add(getModel(result));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		return res;
	}

	@Override
	public Computer find(Predicate<? super Computer> predicate) {
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}

	public static ComputerDAO getInstance() {
		return _instance;
	}
}
