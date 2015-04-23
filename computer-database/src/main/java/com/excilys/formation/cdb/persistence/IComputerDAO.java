package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.model.Computer;

public interface IComputerDAO {
	public enum OrderBy {
		ID("computer.id"), NAME("computer.name"), INTRODUCED(
				"computer.introduced"), DISCONTINUED("computer.discontinued"), COMPANY(
				"company.name");
		private final String name;

		OrderBy(String s) {
			name = s;
		}

		@Override
		public String toString() {
			return name;
		}

		public static OrderBy map(String s) {
			if (ID.name().equalsIgnoreCase(s)) {
				return ID;
			} else if (NAME.name().equalsIgnoreCase(s)) {
				return NAME;
			} else if (INTRODUCED.name().equalsIgnoreCase(s)) {
				return INTRODUCED;
			} else if (DISCONTINUED.name().equalsIgnoreCase(s)) {
				return DISCONTINUED;
			} else if (COMPANY.name().equalsIgnoreCase(s)) {
				return COMPANY;
			}
			return null;
		}
	}

	/**
	 * @return all row of the table Computer
	 */
	public List<Computer> findAll();

	/**
	 * @return all row of the table Computer where has this company
	 */
	public List<Computer> findAllByCompany(Long companyId);

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
	public int remove(Long model);

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
	public Computer find(Long id);

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

	/**
	 * 
	 * @param limit
	 *            number of elements
	 * @param offset
	 *            first element
	 * @return list of limit element started by offset
	 */
	public List<Computer> pagination(int limit, int offset);

	/**
	 * 
	 * @param search
	 *            name of the computer to search
	 * @param limit
	 *            number of elements
	 * @param offset
	 *            first element
	 * @return list of limit element started by offset
	 */
	public List<Computer> pagination(String search, int limit, int offset);

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
	public List<Computer> pagination(String search, int limit, int offset,
			OrderBy ob, boolean asc);

	/**
	 * 
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
	public List<Computer> pagination(int limit, int offset, OrderBy ob,
			boolean asc);
}
