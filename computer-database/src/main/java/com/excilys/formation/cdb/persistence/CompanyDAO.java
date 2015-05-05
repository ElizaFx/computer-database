package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

/**
 *
 * @author Joxit
 */
@Repository
public class CompanyDAO implements ICompanyDAO {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAO.class);

	@Override
	public Company find(Long id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Company res = null;
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.find(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("SELECT * FROM company WHERE ID=?");
			ps.setObject(1, id);
			result = ps.executeQuery();
			if (result.next()) {
				res = CompanyMapper.toModel(result);
			}
		} catch (SQLException e) {
			LOGGER.error("Error in CompanyDAO.find(" + id + ")", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(ps, result);
		}
		return res;
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
				res.add(CompanyMapper.toModel(result));
			}
		} catch (SQLException e) {
			LOGGER.error("Error in CompanyDAO.findAll()", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(statement, result);
		}
		return res;
	}

	@Override
	public Company find(Predicate<? super Company> predicate) {
		if (predicate == null) {
			LOGGER.error("Error param null in CompanyDAO.find(predicate)");
			throw new DAOException("NullPointerException: Predicate null!");
		}
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}

	@Override
	public int count() {
		int res = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT count(*) as size FROM company");
			if (result.next()) {
				res = result.getInt("size");
			}
		} catch (SQLException e) {
			LOGGER.error("Error in CompanyDAO.count()", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(statement, result);
		}
		return res;
	}

	@Override
	public int remove(Long id) {
		int res = 0;
		PreparedStatement ps = null;
		Connection connection = ConnectionFactory.getConnection();
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		try {
			ps = connection
					.prepareStatement("DELETE FROM company WHERE company.id=?");
			ps.setLong(1, id);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error in CompanyDAO.remove(" + id + ")", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(ps, null);
		}
		LOGGER.info("Company id : {} removed", id);
		return res;
	}

}
