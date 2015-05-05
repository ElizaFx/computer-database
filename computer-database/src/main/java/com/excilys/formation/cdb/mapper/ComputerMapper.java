package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.model.Computer.ComputerBuilder;
import com.excilys.formation.cdb.util.Util;

public class ComputerMapper {

	/**
	 * @param result
	 * @return result translated in the model
	 */
	public static Computer toModel(ResultSet result) {
		try {
			ComputerBuilder computer = Computer.build()
					.id(result.getLong("computer.id"))
					.name(result.getString("computer.name"));
			computer.introduced(Util.toLocalDate(result
					.getTimestamp("computer.introduced")));
			computer.discontinued(Util.toLocalDate(result
					.getTimestamp("computer.discontinued")));
			if (result.getLong("company_id") != 0l) {
				computer.company(CompanyMapper.toModel(result));
			}
			return computer.create();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public static String getCompanyName(Computer c) {
		return (c == null) || (c.getCompany() == null) ? null : c.getCompany()
				.getName();
	}

	public static Long getCompanyId(Computer c) {
		return (c == null) || (c.getCompany() == null) ? null : c.getCompany()
				.getId();
	}

	public static ComputerDTO toDTO(Computer computer) {
		return ComputerDTO.build().id(computer.getId())
				.name(computer.getName())
				.introduced(Util.formatDate(computer.getIntroduced()))
				.discontinued(Util.formatDate(computer.getDiscontinued()))
				.companyId(getCompanyId(computer))
				.companyName(getCompanyName(computer)).create();
	}

	public static List<ComputerDTO> toDTO(List<Computer> computers) {
		return computers.stream().map(ComputerMapper::toDTO)
				.collect(Collectors.toList());
	}

	public static Computer toModel(ComputerDTO dto) {
		Computer computer = Computer.build().id(dto.getId())
				.name(dto.getName())
				.introduced(Util.parseDate(dto.getIntroduced()))
				.discontinued(Util.parseDate(dto.getDiscontinued())).create();
		if ((dto.getCompanyId() != null) && (dto.getCompanyId() != 0l)) {
			computer.setCompany(CompanyMapper.toModel(CompanyDTO.build()
					.id(dto.getCompanyId()).name(dto.getCompanyName()).create()));
		}
		return computer;
	}

	public static List<Computer> toModel(List<ComputerDTO> dto) {
		return dto.stream().map(ComputerMapper::toModel)
				.collect(Collectors.toList());
	}
}
