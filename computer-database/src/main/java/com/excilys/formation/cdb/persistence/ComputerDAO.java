package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;

/**
 *
 * @author Joxit
 */
@Repository
@Transactional
public class ComputerDAO implements IComputerDAO {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@PersistenceContext
	private EntityManager em;

	@Override
	public Computer find(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in ComputerDAO.find(id)");
			throw new DAOException("NullPointerException: ID null!");
		}
		return em.find(Computer.class, id);
	}

	@Override
	public void insert(Computer computer) {
		if (computer == null) {
			LOGGER.error("Error param null in CompanyDAO.insert(computer)");
			throw new DAOException("NullPointerException: Id null!");
		}
		/* merge fix detached entity passed to persist */
		em.persist(em.merge(computer));
	}

	@Override
	public void remove(Computer computer) {
		if (computer == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(computer)");
			throw new DAOException("NullPointerException: Id null!");
		}
		em.remove(find(computer.getId()));
	}

	@Override
	public void update(Computer computer) {
		if (computer == null) {
			LOGGER.error("Error param null in CompanyDAO.update(model)");
			throw new DAOException("NullPointerException: Model null!");
		}
		computer.setId(em.merge(computer).getId());
	}

	@Override
	public List<Computer> findAll() {
		CriteriaQuery<Computer> cq = em.getCriteriaBuilder().createQuery(
				Computer.class);
		cq.select(cq.from(Computer.class));
		return em.createQuery(cq).getResultList();
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
						"SELECT count(*) as size FROM computer left outer join company on computer.company_id=company.id where computer.name like ? or company.name like ?;",
						(rs, rowNum) -> rs.getInt("size"), search, search);
	}

	@Override
	public int count() {
		CriteriaQuery<Long> cq = em.getCriteriaBuilder()
				.createQuery(Long.class);
		Root<Computer> rt = cq.from(Computer.class);
		cq.select(em.getCriteriaBuilder().count(rt));
		Query q = em.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
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
				+ "computer left outer join company on computer.company_id=company.id where computer.name like ? or company.name like ? order by "
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
				.query("SELECT * FROM computer left outer join company on computer.company_id=company.id where company.id = ?",
						new ComputerMapper(), companyId);
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
