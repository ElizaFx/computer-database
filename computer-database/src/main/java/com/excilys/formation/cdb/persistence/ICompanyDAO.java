package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyDAO {
	/**
	 * @return all row of the table of T
	 */
	public List<Company> findAll();

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public Company find(Predicate<? super Company> predicate);

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	public Company find(Object id);
}
