package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;
import com.excilys.formation.cdb.persistence.IComputerDAO;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private IComputerDAO computerDAO;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyService.class);

	/**
	 * @return all row of the table of T
	 */
	@Override
	public List<Company> findAll() {
		return companyDAO.findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Company find(Predicate<? super Company> predicate) {
		return companyDAO.find(predicate);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Company find(Long id) {
		return companyDAO.find(id);
	}

	@Override
	public int count() {
		return companyDAO.count();
	}

	@Transactional(readOnly = false)
	@Override
	public int remove(Long id) {
		LOGGER.info("Begin of transaction remove compnay " + id);
		int nbComputers = computerDAO.removeByCompany(id);
		companyDAO.remove(id);
		LOGGER.info("End of transaction remove compnay" + id + " "
				+ nbComputers + " computers deleted");
		return nbComputers;
	}
}
