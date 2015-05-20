package com.excilys.formation.cdb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Computer;

@Component
public class ComputerMapper {

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

}
