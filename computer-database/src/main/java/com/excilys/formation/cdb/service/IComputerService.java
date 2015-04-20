package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;

public interface IComputerService extends Paginable<Computer> {
	/**
	 * @return all row of the table of Computer
	 */
	public List<Computer> findAll();

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public Computer find(Predicate<? super Computer> predicate);

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 *
	 * @param model
	 * @return the row count inserted
	 */
	public int insert(Computer model);

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param model
	 * @return the row count removed
	 */
	public int remove(Computer model);

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 *
	 * @param model
	 * @return the row count updated
	 */
	public int update(Computer model);

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	public Computer find(Object id);

	/**
	 * 
	 * @return count the number of computers
	 */
	public int count();

	/**
	 * @param search
	 *            name of the computer to search
	 * @return count the number of computers
	 */
	int count(String search);

}
