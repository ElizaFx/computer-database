package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;
import com.excilys.formation.cdb.ui.Page;
import com.excilys.formation.cdb.util.Util;

@Service
public class ComputerService implements IComputerService {

	@Autowired
	private ComputerDAO computerDAO;

	/**
	 * @return all row of the table of Computer
	 */
	@Override
	public List<Computer> findAll() {
		return computerDAO.findAll();
	}

	/**
	 * @param predicate
	 * @return element which satisfy predicate
	 */
	@Override
	public Computer find(Predicate<? super Computer> predicate) {
		return computerDAO.find(predicate);
	}

	/**
	 * Insert model in the table. Must call insertRequest with the right request
	 *
	 * @param model
	 * @return the row count inserted
	 */
	@Override
	public int insert(Computer model) {
		return computerDAO.insert(model);
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
		return computerDAO.remove(id);
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
		return computerDAO.update(model);
	}

	/**
	 * @param id
	 *            of T model
	 * @return element with this id
	 */
	@Override
	public Computer find(Long id) {
		return computerDAO.find(id);
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
		return ComputerMapper.toDTO(computerDAO.pagination(search, limit,
				offset, ob, asc));
	}

	@Override
	public Page<ComputerDTO> getPage(String search, String sLimit,
			String sCurPage, String sOrderBy, String sAsc) {

		OrderBy ob = OrderBy.map(sOrderBy);
		boolean asc = Boolean.parseBoolean(sAsc);
		if (search == null) {
			search = "";
		}
		int count = count(search);
		int curPage = 1;
		int limit = 10;
		if (Util.isNumeric(sCurPage)) {
			curPage = Integer.parseInt(sCurPage);
		}
		if (Util.isNumeric(sLimit)) {
			limit = Integer.parseInt(sLimit);
		}

		return new Page<>(this, search, count, curPage, limit, 5, ob, asc);

	}

	@Override
	public int remove(List<Long> output) {
		if (output != null) {
			return output.stream().mapToInt(this::remove).sum();
		}
		return 0;
	}

	@Override
	public List<Computer> findAllByCompany(Long companyId) {
		return computerDAO.findAllByCompany(companyId);
	}
}
