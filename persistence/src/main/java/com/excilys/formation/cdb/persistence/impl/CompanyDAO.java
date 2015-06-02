package com.excilys.formation.cdb.persistence.impl;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;

/**
 *
 * @author Joxit
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CompanyDAO implements ICompanyDAO {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAO.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company find(Long id) {
		if (id == null) {
			LOGGER.warn("Error param null in CompanyDAO.find(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		return em.find(Company.class, id);
	}

	@Override
	public List<Company> findAll() {
		CriteriaQuery<Company> cq = em.getCriteriaBuilder().createQuery(
				Company.class);
		cq.select(cq.from(Company.class));
		return em.createQuery(cq).getResultList();
	}

	@Override
	public Company find(Predicate<? super Company> predicate) {
		if (predicate == null) {
			LOGGER.warn("Error param null in CompanyDAO.find(predicate)");
			throw new DAOException("NullPointerException: Predicate null!");
		}
		return findAll().stream().filter(predicate).findFirst().orElse(null);
	}

	@Override
	public int count() {
		CriteriaQuery<Long> cq = em.getCriteriaBuilder()
				.createQuery(Long.class);
		cq.select(em.getCriteriaBuilder().count(cq.from(Company.class)));
		return em.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(Long id) {
		if (id == null) {
			LOGGER.warn("Error param null in CompanyDAO.remove(id)");
			throw new DAOException("NullPointerException: Id null!");
		}
		em.remove(find(id));
	}

}
