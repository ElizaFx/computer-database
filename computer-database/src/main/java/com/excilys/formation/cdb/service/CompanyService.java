package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

public class CompanyService implements ICompanyService {
	public static CompanyService INSTANCE;

	public static CompanyService getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(CompanyService iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyService.class);

	/**
	 * @return all row of the table of T
	 */
	@Override
	public List<Company> findAll() {
		return CompanyDAO.INSTANCE.findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Company find(Predicate<? super Company> predicate) {
		return CompanyDAO.INSTANCE.find(predicate);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Company find(Long id) {
		return CompanyDAO.INSTANCE.find(id);
	}

	@Override
	public int count() {
		return CompanyDAO.INSTANCE.count();
	}

	@Override
	public int remove(Long id) {
		int res = 0;
		try {
			ConnectionFactory.createTransactionConnection();
			ComputerDAO.INSTANCE.findAllByCompany(id).stream()
					.forEach(c -> ComputerDAO.INSTANCE.remove(c.getId()));
			res = CompanyDAO.INSTANCE.remove(id);
			ConnectionFactory.commit();
		} catch (DAOException e) {
			ConnectionFactory.rollback();
			LOGGER.error("Error in CompanyService.remove(" + id
					+ ") rollback successfull", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeTransactionConnection();
		}
		return res;
	}
}
