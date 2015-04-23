package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;

public enum ComputerService implements IComputerService {

	INSTANCE;

	/**
	 * @return all row of the table of Computer
	 */
	@Override
	public List<Computer> findAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Computer find(Predicate<? super Computer> predicate) {
		return ComputerDAO.INSTANCE.find(predicate);
	}

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 *
	 * @param model
	 * @return the row count inserted
	 */
	@Override
	public int insert(Computer model) {
		return ComputerDAO.INSTANCE.insert(model);
	}

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param model
	 * @return the row count removed
	 */
	@Override
	public int remove(Long id) {
		return ComputerDAO.INSTANCE.remove(id);
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
		return ComputerDAO.INSTANCE.update(model);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Computer find(Long id) {
		return ComputerDAO.INSTANCE.find(id);
	}

	@Override
	public int count() {
		return ComputerDAO.INSTANCE.count();
	}

	@Override
	public int count(String search) {
		return ComputerDAO.INSTANCE.count(search);
	}

	@Override
	public List<Computer> pagination(int limit, int offset) {
		return ComputerDAO.INSTANCE.pagination(limit, offset);
	}

	@Override
	public List<Computer> pagination(String search, int limit, int offset) {
		if (search == null) {
			search = "";
		}
		return ComputerDAO.INSTANCE.pagination(search, limit, offset);
	}

	@Override
	public List<Computer> pagination(String search, int limit, int offset,
			OrderBy ob, boolean asc) {
		if (ob == null) {
			ob = OrderBy.ID;
		}
		if (search == null) {
			search = "";
		}
		return ComputerDAO.INSTANCE.pagination(search, limit, offset, ob, asc);
	}

	@Override
	public List<Computer> pagination(int limit, int offset, OrderBy ob,
			boolean asc) {
		return ComputerDAO.INSTANCE.pagination(limit, offset, ob, asc);
	}

}
