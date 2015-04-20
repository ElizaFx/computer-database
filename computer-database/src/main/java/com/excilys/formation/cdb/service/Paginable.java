package com.excilys.formation.cdb.service;

import java.util.List;

public interface Paginable<T> {

	/**
	 * 
	 * @param limit
	 *            number of elements
	 * @param offset
	 *            first element
	 * @return list of limit element started by offset
	 */
	public List<T> pagination(int limit, int offset);

	/**
	 * 
	 * @param search
	 *            filter to show elements
	 * @param limit
	 *            number of elements
	 * @param offset
	 *            first element
	 * @return list of limit element started by offset
	 */
	public List<T> pagination(String search, int limit, int offset);
}
