package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyService {
	/**
	 * @return all row of company table
	 */
	public List<Company> findAll();

	/**
	 * @return all row of company table ordered by name
	 */
	public List<Company> findAllOrderByName();

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
	 * @return number of computer deleted
	 */
	public int remove(Long id);
}
