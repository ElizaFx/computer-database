package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;

/**
 *
 * @author Joxit
 */
@Repository
public class CompanyDAO implements ICompanyDAO {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(jdbcTemplate);
	}

	@Override
	public Company find(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.find(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		return jdbcTemplate
				.query("SELECT * FROM company WHERE ID=?", new CompanyMapper(),
						id).stream().findFirst().orElse(null);
	}

	@Override
	public List<Company> findAll() {
		return jdbcTemplate.query("select * from company", new CompanyMapper());
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
		return jdbcTemplate.queryForObject(
				"SELECT count(*) as size FROM company",
				(rs, rowNum) -> rs.getInt("size"));
	}

	@Override
	public int remove(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		return jdbcTemplate
				.update("DELETE FROM company WHERE company.id=?", id);
	}

}
