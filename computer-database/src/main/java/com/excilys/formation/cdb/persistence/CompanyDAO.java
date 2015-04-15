package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

/**
 *
 * @author Joxit
 */
public enum CompanyDAO implements ICompanyDAO {

	_instance;

	@Override
	public Company find(Object id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Company res = null;
		if (id == null) {
			throw new DAOException("NullPointerException: Model null!");
		}
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("SELECT * FROM company WHERE ID=?");
			ps.setObject(1, id);
			result = ps.executeQuery();
			if (result.next()) {
				res = CompanyMapper.getModel(result);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, ps, result);
		}
		return res;
	}

	public static CompanyDAO getInstance() {
		return _instance;
	}

	@Override
	public List<Company> findAll() {
		List<Company> res = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("select * from company");
			while (result.next()) {
				res.add(CompanyMapper.getModel(result));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		return res;
	}

	@Override
	public Company find(Predicate<? super Company> predicate) {
		if (predicate == null) {
			throw new DAOException("NullPointerException: Predicate null!");
		}
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}
}
