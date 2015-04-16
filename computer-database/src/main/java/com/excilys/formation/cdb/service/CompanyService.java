package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;

public enum CompanyService implements ICompanyService {
	_instance;

	/**
	 * @return all row of the table of T
	 */
	@Override
	public List<Company> findAll() {
		return CompanyDAO.getInstance().findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Company find(Predicate<? super Company> predicate) {
		return CompanyDAO.getInstance().find(predicate);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Company find(Object id) {
		return CompanyDAO.getInstance().find(id);
	}

	public int count() {
		return CompanyDAO.getInstance().count();
	}

	public static CompanyService getInstance() {
		return _instance;
	}
}
