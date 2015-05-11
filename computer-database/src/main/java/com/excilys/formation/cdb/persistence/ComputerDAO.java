package com.excilys.formation.cdb.persistence;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
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
	private JdbcTemplate jdbcTemplate;

	@Override
	public Computer find(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in ComputerDAO.find(id)");
			throw new DAOException("NullPointerException: ID null!");
		}
		return jdbcTemplate
				.query("SELECT * FROM computer left outer join company on computer.company_id=company.id "
						+ "WHERE computer.ID=?", new ComputerMapper(), id)
				.stream().findFirst().orElse(null);
	}

	@Override
	public int insert(Computer model) {
		int res = 0;
		if (model == null) {
			LOGGER.error("Error param null in ComputerDAO.insert(id)");
			throw new DAOException("NullPointerException: Model null!");
		}
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps1 = con
					.prepareStatement(
							"INSERT INTO computer SET NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=?",
							Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, model.getName());
			ps1.setDate(2, Util.toSqlDate(model.getIntroduced()));
			ps1.setDate(3, Util.toSqlDate(model.getDiscontinued()));
			if (model.getCompany() == null) {
				ps1.setNull(4, Types.BIGINT);
			} else {
				ps1.setLong(4, model.getCompany().getId());
			}
			return ps1;
		};

		KeyHolder keyHolder = new GeneratedKeyHolder();
		res = jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		model.setId(key != null ? key.longValue() : null);
		return res;
	}

	@Override
	public int remove(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}

		return jdbcTemplate.update("DELETE FROM computer WHERE ID=?", id);
	}

	@Override
	public int update(Computer model) {
		if (model == null) {
			LOGGER.error("Error param null in CompanyDAO.update(model)");
			throw new DAOException("NullPointerException: Model null!");
		}
		return jdbcTemplate
				.update("UPDATE computer SET "
						+ "NAME=?, INTRODUCED=?, DISCONTINUED=?, COMPANY_ID=? WHERE ID=?",
						model.getName(), Util.toSqlDate(model.getIntroduced()),
						Util.toSqlDate(model.getDiscontinued()), model
								.getCompany() != null ? model.getCompany()
								.getId() : null, model.getId());
	}

	@Override
	public List<Computer> findAll() {
		return jdbcTemplate
				.query("SELECT * FROM "
						+ "computer left outer join company on computer.company_id=company.id",
						new ComputerMapper());
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
		search = search != null ? "%" + search + "%" : "%";
		return jdbcTemplate
				.queryForObject(
						"SELECT count(*) as size FROM "
								+ "computer left outer join company on computer.company_id=company.id "
								+ "where computer.name like ? or company.name like ?;",
						(rs, rowNum) -> rs.getInt("size"), search, search);
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
		String sql = "SELECT * from "
				+ "computer left outer join company on computer.company_id=company.id "
				+ "where computer.name like ? or company.name like ? order by "
				+ (ob == null ? OrderBy.ID : ob) + (asc ? " " : " desc ")
				+ "limit ? offset ? ";
		search = search != null ? "%" + search + "%" : "%";
		return jdbcTemplate.query(sql, new ComputerMapper(), search, search,
				limit, offset);
	}

	@Override
	public List<Computer> findAllByCompany(Long companyId) {
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		return jdbcTemplate
				.query("SELECT * FROM "
						+ "computer left outer join company on computer.company_id=company.id "
						+ "where company.id = ?", new ComputerMapper(),
						companyId);
	}

	@Override
	public int removeByCompany(Long companyId) {
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}

		return jdbcTemplate.update("DELETE FROM computer where company_id = ?",
				companyId);
	}
}
