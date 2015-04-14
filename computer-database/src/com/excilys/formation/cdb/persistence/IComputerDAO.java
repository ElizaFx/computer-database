package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;

public interface IComputerDAO {
	/**
	 * @return all row of the table of T
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
	public abstract int insert(Computer model);

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param model
	 * @return the row count removed
	 */
	public abstract int remove(Computer model);

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 *
	 * @param model
	 * @return the row count updated
	 */
	public abstract int update(Computer model);

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	public abstract Computer find(Object id);

	/**
	 * @param result
	 * @return result translated in the model
	 */
	public abstract Computer getModel(ResultSet result);
}
