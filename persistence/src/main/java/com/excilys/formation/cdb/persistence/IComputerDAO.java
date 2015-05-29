package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;

import com.excilys.formation.cdb.model.Company;
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
			return NAME;
		}

		public Path<Object> toOrder(Join<Computer, Company> join) {
			if (equals(COMPANY)) {
				return join.get("name");
			}
			return join.getParent().get(name().toLowerCase());
		}
	}

	/**
	 * @return all row of the table Computer
	 */
	public List<Computer> findAll();

	/**
	 * @param companyId
	 * @return all row of the table Computer which has this company
	 */
	public List<Computer> findAllByCompany(Long companyId);

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public Computer find(Predicate<? super Computer> predicate);

	/**
	 * Insert model in the table. Model is also updated
	 *
	 * @param model
	 */
	public void insert(Computer model);

	/**
	 * Remove model from the table.
	 *
	 * @param id
	 */
	public void remove(Long id);

	/**
	 * Update model from the table.
	 *
	 * @param model
	 */
	public void update(Computer model);

	/**
	 * @param id
	 * @return computer with this id
	 */
	public Computer find(Long id);

	/**
	 * @return count the number of computers
	 */
	public int count();

	/**
	 * @param search
	 *            name of the computer or company to search
	 * @return count the number of computers
	 */
	public int count(String search);

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
	 * @param companyId
	 * @return remove all computer with this companyId
	 */
	public int removeByCompany(Long companyId);
}
