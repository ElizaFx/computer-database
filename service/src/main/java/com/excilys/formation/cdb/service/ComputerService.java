package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;
import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@Service
public class ComputerService implements IComputerService {

	@Autowired
	private IComputerDAO computerDAO;

	@Autowired
	private ComputerMapper computerMapper;

	/**
	 * @return all row of the table of Computer
	 */
	@Override
	public List<ComputerDTO> findAll() {
		return computerMapper.toDTO(computerDAO.findAll());
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public ComputerDTO find(Predicate<? super Computer> predicate) {
		return computerMapper.toDTO(computerDAO.find(predicate));
	}

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 *
	 * @param model
	 * @return the row count inserted
	 */
	@Override
	@Transactional(readOnly = false)
	public void insert(ComputerDTO model) {
		Computer computer = computerMapper.toModel(model);
		computerDAO.insert(computer);
		model.setId(computer.getId());
	}

	/**
	 * Remove model from the table. Must call removeRequest with the right
	 * request
	 *
	 * @param model
	 */
	@Override
	@Transactional(readOnly = false)
	public void remove(ComputerDTO model) {
		computerDAO.remove(model == null ? null : model.getId());
	}

	/**
	 * Update model from the table. Must call updateRequest with the right id
	 * and request
	 *
	 * @param model
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(ComputerDTO model) {
		computerDAO.update(computerMapper.toModel(model));
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public ComputerDTO find(Long id) {
		return computerMapper.toDTO(computerDAO.find(id));
	}

	@Override
	public int count() {
		return computerDAO.count();
	}

	@Override
	public int count(String search) {
		if (search == null) {
			search = "";
		}
		return computerDAO.count(search);
	}

	@Override
	public List<ComputerDTO> pagination(String search, int limit, int offset,
			OrderBy ob, boolean asc) {
		if (ob == null) {
			ob = OrderBy.ID;
		}
		if (search == null) {
			search = "";
		}
		if (limit < 0) {
			limit = 10;
		}
		if (offset < 0) {
			offset = 0;
		}
		return computerMapper.toDTO(computerDAO.pagination(search, limit,
				offset, ob, asc));
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(List<Long> output) {
		if (output != null) {
			output.stream().forEach(computerDAO::remove);
		}
	}

	@Override
	public List<ComputerDTO> findAllByCompany(Long companyId) {
		return computerMapper.toDTO(computerDAO.findAllByCompany(companyId));
	}
}
