package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Computer;

public interface IComputerService extends Paginable<ComputerDTO> {
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
	public void insert(Computer model);

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param id
	 */
	public void remove(Computer id);

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 *
	 * @param model
	 */
	public void update(Computer model);

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
	@Override
	public int count(String search);

	public Pagination<ComputerDTO> getPage(String search, String limit,
			String curPage, String orderBy, String asc);

	/**
	 * 
	 * @param ids
	 *            of all computers
	 * @return number of deletions
	 */
	public void remove(List<Long> ids);

	/**
	 * 
	 * @param companyId
	 * @return list of all computers from this company
	 */
	public List<Computer> findAllByCompany(Long companyId);
}
