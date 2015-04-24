package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.util.Util;

public class ComputerMapper {

	/**
	 * @param result
	 * @return result translated in the model
	 */
	public static Computer getModel(ResultSet result) {
		Computer computer = null;
		if (result == null) {
			return null;
		}
		try {
			computer = new Computer();
			computer.setId(result.getLong("computer.id"));
			computer.setName(result.getString("computer.name"));
			Timestamp tmp = result.getTimestamp("computer.introduced");
			computer.setIntroduced(tmp == null ? null : tmp.toLocalDateTime()
					.toLocalDate());
			tmp = result.getTimestamp("computer.discontinued");
			computer.setDiscontinued(tmp == null ? null : tmp.toLocalDateTime()
					.toLocalDate());
			if (result.getLong("company_id") != 0l) {
				computer.setCompany(CompanyMapper.getModel(result));
			} else {
				computer.setCompany(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer;
	}

	public static String getCompanyName(Computer c) {
		if ((c == null) || (c.getCompany() == null)) {
			return "";
		}
		return c.getCompany().getName();
	}

	public static ComputerDTO computerModelToDTO(Computer computer) {
		ComputerDTO res = new ComputerDTO();
		res.setId(computer.getId());
		res.setName(computer.getName());
		res.setIntroduced(Util.formatDate(computer.getIntroduced()));
		res.setDiscontinued(Util.formatDate(computer.getDiscontinued()));
		if (computer.getCompany() != null) {
			res.setCompanyId(computer.getCompany().getId());
			res.setCompanyName(String.valueOf(computer.getCompany().getName()));
		}
		return res;
	}

	public static List<ComputerDTO> computerModelToDTO(List<Computer> computers) {
		List<ComputerDTO> res = new ArrayList<ComputerDTO>();
		computers.forEach(c -> res.add(computerModelToDTO(c)));
		return res;
	}

	public static Computer computerDTOToModel(ComputerDTO dto) {
		if (dto == null) {
			return null;
		}
		Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		if ((dto.getIntroduced() != null) && !dto.getIntroduced().isEmpty()) {
			computer.setIntroduced(Util.parseDate(dto.getIntroduced()));
		}
		if ((dto.getDiscontinued() != null) && !dto.getDiscontinued().isEmpty()) {
			computer.setDiscontinued(Util.parseDate(dto.getDiscontinued()));
		}
		if ((dto.getCompanyId() != null) && (dto.getCompanyId() != 0l)) {
			CompanyDTO company = new CompanyDTO();
			company.setId(dto.getCompanyId());
			company.setName(dto.getCompanyName());
			computer.setCompany(CompanyMapper.companyDTOToModel(company));
		}

		return computer;
	}

	public static List<Computer> computerDTOToModel(List<ComputerDTO> dto) {
		List<Computer> res = new ArrayList<>();
		dto.forEach(c -> res.add(computerDTOToModel(c)));
		return res;
	}
}
