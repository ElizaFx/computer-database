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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;
import com.excilys.formation.cdb.util.Util;

/**
 *
 * @author Joxit
 */
@Repository
public class ComputerDAO implements IComputerDAO {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	@Autowired
	private ConnectionFactory connectionFacotry;

	@Override
	public Computer find(Long id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Computer res = null;
		if (id == null) {
			LOGGER.error("Error param null in ComputerDAO.find(id)");
			throw new DAOException("NullPointerException: ID null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			ps = connection
					.prepareStatement("SELECT * FROM computer left outer join company on computer.company_id=company.id "
							+ "WHERE computer.ID=?");
			ps.setObject(1, id);
			result = ps.executeQuery();
			if (result.next()) {
				res = ComputerMapper.toModel(result);
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.find(" + id + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, ps, result);
		}
		return res;
	}

	@Override
	public int insert(Computer model) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (model == null) {
			LOGGER.error("Error param null in ComputerDAO.insert(id)");
			throw new DAOException("NullPointerException: Model null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			ps = connection
					.prepareStatement(
							"INSERT INTO computer SET NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=?",
							Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, model.getName());
			ps.setDate(2, Util.toSqlDate(model.getIntroduced()));
			ps.setDate(3, Util.toSqlDate(model.getDiscontinued()));
			if (model.getCompany() == null) {
				ps.setNull(4, Types.BIGINT);
			} else {
				ps.setLong(4, model.getCompany().getId());
			}
			res = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				model.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.insert(" + model + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, ps, rs);
		}
		return res;
	}

	@Override
	public int remove(Long id) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			ps = connection.prepareStatement("DELETE FROM computer WHERE ID=?");
			ps.setLong(1, id);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.remove(" + id + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, ps, null);
		}
		LOGGER.info("Computer id : {} removed", id);
		return res;
	}

	@Override
	public int update(Computer model) {
		int res = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		if (model == null) {
			LOGGER.error("Error param null in CompanyDAO.update(model)");
			throw new DAOException("NullPointerException: Model null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			ps = connection
					.prepareStatement("UPDATE computer SET "
							+ "NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=? WHERE ID=?");
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
			LOGGER.error("Error in ComputerDAO.update(" + model + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, ps, null);
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
			connection = connectionFacotry.getConnection();
			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT * FROM "
							+ "computer left outer join company on computer.company_id=company.id");
			while (result.next()) {
				res.add(ComputerMapper.toModel(result));
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.findAll()", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, statement, result);
		}
		return res;
	}

	@Override
	public Computer find(Predicate<? super Computer> predicate) {
		if (predicate == null) {
			LOGGER.error("Error param null in CompanyDAO.find(predicate)");
			throw new DAOException("NullPointerException: Predicate null!");
		}
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}

	@Override
	public int count(String search) {
		int res = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = connectionFacotry.getConnection();
			statement = connection
					.prepareStatement("SELECT count(*) as size FROM "
							+ "computer left outer join company on computer.company_id=company.id "
							+ "where computer.name like ? or company.name like ?;");
			if (search != null) {
				statement.setString(1, "%" + search + "%");
				statement.setString(2, "%" + search + "%");
			} else {
				statement.setString(1, "%");
				statement.setString(2, "%");
			}
			result = statement.executeQuery();
			if (result.next()) {
				res = result.getInt("size");
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.find(" + search + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, statement, result);
		}
		return res;
	}

	@Override
	public int count() {
		return count("");
	}

	@Override
	public List<Computer> pagination(int limit, int offset) {
		return pagination("", limit, offset);
	}

	@Override
	public List<Computer> pagination(int limit, int offset, OrderBy ob,
			boolean asc) {
		return pagination("", limit, offset, ob, asc);
	}

	@Override
	public List<Computer> pagination(String search, int limit, int offset) {
		return pagination(search, limit, offset, OrderBy.ID, true);
	}

	@Override
	public List<Computer> pagination(String search, int limit, int offset,
			OrderBy ob, boolean asc) {
		List<Computer> res = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = connectionFacotry.getConnection();
			statement = connection
					.prepareStatement("SELECT * from "
							+ "computer left outer join company on computer.company_id=company.id "
							+ "where computer.name like ? or company.name like ? order by "
							+ (ob == null ? OrderBy.ID : ob)
							+ (asc ? " " : " desc ") + "limit ? offset ? ");
			if (search != null) {
				statement.setString(1, "%" + search + "%");
				statement.setString(2, "%" + search + "%");
			} else {
				statement.setString(1, "%");
				statement.setString(2, "%");
			}
			statement.setInt(3, limit);
			statement.setInt(4, offset);
			result = statement.executeQuery();

			while (result.next()) {
				res.add(ComputerMapper.toModel(result));
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.pagination(" + search + ","
					+ limit + "," + offset + "," + ob + "," + asc + ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, statement, result);
		}
		return res;
	}

	@Override
	public List<Computer> findAllByCompany(Long companyId) {
		List<Computer> res = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM "
							+ "computer left outer join company on computer.company_id=company.id "
							+ "where company.id = ?");
			statement.setLong(1, companyId);
			result = statement.executeQuery();
			while (result.next()) {
				res.add(ComputerMapper.toModel(result));
			}
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.findAllByCompany(" + companyId
					+ ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, statement, result);
		}
		return res;
	}

	@Override
	public int removeByCompany(Long companyId) {
		int res = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		try {
			connection = connectionFacotry.getConnection();
			statement = connection
					.prepareStatement("DELETE FROM computer where company_id = ?");
			statement.setLong(1, companyId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error in ComputerDAO.findAllByCompany(" + companyId
					+ ")", e);
			throw new DAOException(e);
		} finally {
			connectionFacotry.close(connection, statement, result);
		}
		return res;
	}
}
