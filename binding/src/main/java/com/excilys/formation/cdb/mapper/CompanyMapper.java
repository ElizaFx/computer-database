package com.excilys.formation.cdb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.model.Company;

public class CompanyMapper {

	public static CompanyDTO toDTO(Company company) {
		return CompanyDTO.builder().id(company.getId()).name(company.getName())
				.builder();
	}

	public static List<CompanyDTO> toDTO(List<Company> companies) {
		return companies.parallelStream().map(CompanyMapper::toDTO)
				.collect(Collectors.toList());
	}

	public static Company toModel(CompanyDTO dto) {
		return Company.builder().id(dto.getId()).name(dto.getName()).build();
	}

	public static List<Company> toModel(List<CompanyDTO> companies) {
		return companies.parallelStream().map(CompanyMapper::toModel)
				.collect(Collectors.toList());
	}

}
