package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.model.Computer.ComputerBuilder;
import com.excilys.formation.cdb.util.Util;

@Component
public class ComputerMapper implements RowMapper<Computer> {

	@Autowired
	private DateLocaleMapper dateLocaleMapper;

	public static String getCompanyName(Computer c) {
		return (c == null) || (c.getCompany() == null) ? null : c.getCompany()
				.getName();
	}

	public static Long getCompanyId(Computer c) {
		return (c == null) || (c.getCompany() == null) ? null : c.getCompany()
				.getId();
	}

	public ComputerDTO toDTO(Computer computer) {
		return ComputerDTO
				.build()
				.id(computer.getId())
				.name(computer.getName())
				.introduced(dateLocaleMapper.toString(computer.getIntroduced()))
				.discontinued(
						dateLocaleMapper.toString(computer.getDiscontinued()))
				.companyId(getCompanyId(computer))
				.companyName(getCompanyName(computer)).create();
	}

	public List<ComputerDTO> toDTO(List<Computer> computers) {
		return computers.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Computer toModel(ComputerDTO dto) {
		Computer computer = Computer
				.build()
				.id(dto.getId())
				.name(dto.getName())
				.introduced(dateLocaleMapper.toLocalDate(dto.getIntroduced()))
				.discontinued(
						dateLocaleMapper.toLocalDate(dto.getDiscontinued()))
				.create();
		if ((dto.getCompanyId() != null) && (dto.getCompanyId() != 0l)) {
			computer.setCompany(CompanyMapper.toModel(CompanyDTO.build()
					.id(dto.getCompanyId()).name(dto.getCompanyName()).create()));
		}
		return computer;
	}

	public List<Computer> toModel(List<ComputerDTO> dto) {
		return dto.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComputerBuilder computer = Computer.build()
				.id(rs.getLong("computer.id"))
				.name(rs.getString("computer.name"));
		computer.introduced(Util.toLocalDate(rs
				.getTimestamp("computer.introduced")));
		computer.discontinued(Util.toLocalDate(rs
				.getTimestamp("computer.discontinued")));
		if (rs.getLong("company_id") != 0l) {
			computer.company(CompanyMapper.toModel(rs));
		}
		return computer.create();
	}
}
