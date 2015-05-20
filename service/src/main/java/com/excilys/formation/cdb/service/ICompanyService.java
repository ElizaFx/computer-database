package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyService {
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
	 *            of Company
	 * @return element with this id
	 */
	public Company find(Long id);

	/**
	 * 
	 * @return count the number of companies
	 */
	public int count();

	/**
	 * 
	 * @param id
	 *            of Company
	 * @return -1 if something wrong
	 */
	public int remove(Long id);
}
