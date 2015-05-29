package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyDAO {

	/**
	 * @return all row of company table
	 */
	public List<Company> findAll();

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public Company find(Predicate<? super Company> predicate);

	/**
	 * @param id
	 *            of company
	 * @return company or null
	 */
	public Company find(Long id);

	/**
	 * @return count the number of companies
	 */
	public int count();

	/**
	 * Remove company from the table
	 *
	 * @param id
	 *            of the company
	 */
	public void remove(Long id);
}
