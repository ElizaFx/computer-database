package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

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
		ComputerDTO res = ComputerDTO.build().id(computer.getId())
				.name(computer.getName())
				.introduced(Util.formatDate(computer.getIntroduced()))
				.discontinued(Util.formatDate(computer.getDiscontinued()))
				.create();
		if (computer.getCompany() != null) {
			res.setCompanyId(computer.getCompany().getId());
			res.setCompanyName(String.valueOf(computer.getCompany().getName()));
		}
		return res;
	}

	public static List<ComputerDTO> computerModelToDTO(List<Computer> computers) {
		return computers.parallelStream()
				.map(ComputerMapper::computerModelToDTO)
				.collect(Collectors.toList());
	}

	public static Computer computerDTOToModel(ComputerDTO dto) {
		if (dto == null) {
			return null;
		}
		Computer computer = Computer.build().id(dto.getId())
				.name(dto.getName())
				.introduced(Util.parseDate(dto.getIntroduced()))
				.discontinued(Util.parseDate(dto.getDiscontinued())).create();
		if ((dto.getCompanyId() != null) && (dto.getCompanyId() != 0l)) {
			CompanyDTO company = CompanyDTO.build().id(dto.getCompanyId())
					.name(dto.getCompanyName()).create();
			computer.setCompany(CompanyMapper.companyDTOToModel(company));
		}
		return computer;
	}

	public static List<Computer> computerDTOToModel(List<ComputerDTO> dto) {
		return dto.parallelStream().map(ComputerMapper::computerDTOToModel)
				.collect(Collectors.toList());
	}
}
