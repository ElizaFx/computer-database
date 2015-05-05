package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private ConnectionFactory connectionFacotry;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyService.class);

	/**
	 * @return all row of the table of T
	 */
	@Override
	public List<Company> findAll() {
		System.out.println(TransactionSynchronizationManager
				.isSynchronizationActive());
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

	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int remove(Long id) {
		int res = 0;
		System.out.println(TransactionSynchronizationManager
				.isSynchronizationActive());
		System.out.println(connectionFacotry.getConnection());

		connectionFacotry.createTransactionConnection();
		System.out.println(connectionFacotry.getConnection());
		computerDAO.findAllByCompany(id).stream()
				.forEach(c -> computerDAO.remove(c.getId()));
		System.out.println(connectionFacotry.getConnection());
		res = companyDAO.remove(id);

		connectionFacotry.closeTransactionConnection();

		return res;
	}
}
