package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

/**
 *
 * @author Joxit
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ComputerDAO implements IComputerDAO {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

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
	@Transactional(readOnly = false)
	public void insert(Computer computer) {
		if (computer == null) {
			LOGGER.error("Error param null in CompanyDAO.insert(computer)");
			throw new DAOException("NullPointerException: Id null!");
		}
		/* merge fix detached entity passed to persist */
		Computer c = em.merge(computer);
		em.persist(c);
		computer.setId(c != null ? c.getId() : null);
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(Long id) {
		if (id == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(computer)");
			throw new DAOException("NullPointerException: Id null!");
		}
		Computer computer = find(id);
		if (computer != null) {
			em.remove(computer);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Computer computer) {
		if (computer == null) {
			LOGGER.error("Error param null in CompanyDAO.update(model)");
			throw new DAOException("NullPointerException: Model null!");
		}
		em.merge(computer);
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
		search = search == null ? "%" : "%" + search + "%";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Join<Computer, Company> leftJoin = cq.from(Computer.class).join(
				"company", JoinType.LEFT);
		// use .join and JoinType.LEFT because default join when selecting table
		// field is cross join
		cq.select(cb.count(leftJoin.getParent().get("id"))).where(
				cb.or(cb.like(leftJoin.get("name"), search),
						cb.like(leftJoin.getParent().get("name"), search)));

		return em.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public int count() {
		CriteriaQuery<Long> cq = em.getCriteriaBuilder()
				.createQuery(Long.class);
		cq.select(em.getCriteriaBuilder().count(cq.from(Computer.class)));
		return em.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public List<Computer> pagination(String search, int limit, int offset,
			OrderBy ob, boolean asc) {
		search = search == null ? "%" : "%" + search + "%";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);
		Join<Computer, Company> leftJoin = cq.from(Computer.class).join(
				"company", JoinType.LEFT);
		// use .join and JoinType.LEFT because default join when selecting table
		// field is cross join
		cq.select(leftJoin.getParent()).where(
				cb.or(cb.like(leftJoin.get("name"), search),
						cb.like(leftJoin.getParent().get("name"), search)));
		cq.orderBy(asc ? cb.asc(ob.toOrder(leftJoin)) : cb.desc(ob
				.toOrder(leftJoin)));
		return em.createQuery(cq).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
	}

	@Override
	public List<Computer> findAllByCompany(Long companyId) {
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);
		Join<Computer, Company> leftJoin = cq.from(Computer.class).join(
				"company", JoinType.LEFT);
		// use .join and JoinType.LEFT because default join when selecting table
		// field is cross join
		cq.select(leftJoin.getParent()).where(
				cb.equal(leftJoin.get("id"), companyId));
		return em.createQuery(cq).getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public int removeByCompany(Long companyId) {
		if (companyId == null) {
			LOGGER.error("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Computer> cq = cb.createCriteriaDelete(Computer.class);
		Root<Computer> rt = cq.from(Computer.class);

		cq.where(cb.equal(rt.get("company").get("id"), companyId));
		return em.createQuery(cq).executeUpdate();
	}
}
