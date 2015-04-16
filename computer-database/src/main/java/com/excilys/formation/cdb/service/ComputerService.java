package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public enum ComputerService implements IComputerService {

	_instance;

	/**
	 * @return all row of the table of Computer
	 */
	@Override
	public List<Computer> findAll() {
		return ComputerDAO.getInstance().findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Computer find(Predicate<? super Computer> predicate) {
		return ComputerDAO.getInstance().find(predicate);
	}

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 *
	 * @param model
	 * @return the row count inserted
	 */
	@Override
	public int insert(Computer model) {
		return ComputerDAO.getInstance().insert(model);
	}

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param model
	 * @return the row count removed
	 */
	@Override
	public int remove(Computer model) {
		return ComputerDAO.getInstance().remove(model);
	}

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 *
	 * @param model
	 * @return the row count updated
	 */
	@Override
	public int update(Computer model) {
		return ComputerDAO.getInstance().update(model);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Computer find(Object id) {
		return ComputerDAO.getInstance().find(id);
	}

	@Override
	public int count() {
		return ComputerDAO.getInstance().count();
	}

	public static ComputerService getInstance() {
		return _instance;
	}

	@Override
	public List<Computer> pagination(int limit, int offset) {
		return ComputerDAO.getInstance().pagination(limit, offset);
	}

}
