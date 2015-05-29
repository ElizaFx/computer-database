package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Computer;

public interface IComputerService extends Paginable<ComputerDTO> {
	/**
	 * @return all row of the table of Computer
	 */
	public List<ComputerDTO> findAll();

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	public ComputerDTO find(Predicate<? super Computer> predicate);

	/**
	 * Insert model in the table. Model is also updated
	 *
	 * @param model
	 */
	public void insert(ComputerDTO model);

	/**
	 * Remove model from the table.
	 *
	 * @param id
	 */
	public void remove(ComputerDTO id);

	/**
	 * Update model from the table.
	 *
	 * @param model
	 */
	public void update(ComputerDTO model);

	/**
	 * @param id
	 * @return computer with this id
	 */
	public ComputerDTO find(Long id);

	/**
	 * @return count the number of computers
	 */
	public int count();

	/**
	 * @param search
	 *            name of the computer or company to search
	 * @return count the number of computers
	 */
	@Override
	public int count(String search);

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
	public List<ComputerDTO> findAllByCompany(Long companyId);
}
