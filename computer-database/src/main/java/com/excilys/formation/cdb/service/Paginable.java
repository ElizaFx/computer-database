package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;

public interface Paginable<T> {

	/**
	 * 
	 * @param search
	 *            name of the computer to search
	 * @param limit
	 *            number of elements
	 * @param offset
	 *            first element
	 * @param ob
	 *            order by
	 * @param asc
	 *            true for ascending order, false for descending order
	 * @return list of limit element started by offset
	 */
	public List<T> pagination(String search, int limit, int offset, OrderBy ob,
			boolean asc);

}
