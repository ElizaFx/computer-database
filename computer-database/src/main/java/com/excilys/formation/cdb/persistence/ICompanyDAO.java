package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
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
	public Company find(Long id);

	/**
	 * 
	 * @return count the number of companies
	 */
	public int count();

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param connection
	 *            for a transaction
	 * @param model
	 * @return the row count removed
	 */
	public int remove(Connection connection, Long model);
}
