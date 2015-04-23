package com.excilys.formation.cdb.service;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

public enum CompanyService implements ICompanyService {
	INSTANCE;

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
		Connection connection = null;
		int res = 0;
		try {
			connection = ConnectionFactory.getTransactionConnection();
			final Connection finalConnection = connection;
			ComputerDAO.INSTANCE
					.findAllByCompany(id)
					.parallelStream()
					.forEach(
							c -> ComputerDAO.INSTANCE.remove(finalConnection,
									c.getId()));

			res = CompanyDAO.INSTANCE.remove(connection, id);
			ConnectionFactory.commit(connection);
		} catch (DAOException e) {
			ConnectionFactory.rollback(connection);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, null, null);
		}
		return res;
	}
}
