package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;

public class CompanyMapper {
	/**
	 * @param result
	 * @return result translated in the model
	 */
	public static Company getModel(ResultSet result) {
		Company company = null;
		if (result == null) {
			return null;
		}
		try {
			company = new Company();
			company.setId(result.getLong("company.id"));
			company.setName(result.getString("company.name"));
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return company;
	}

	public static CompanyDTO companyModelToDTO(Company company) {
		if (company == null) {
			return null;
		}
		return CompanyDTO.build().id(company.getId()).name(company.getName())
				.create();
	}

	public static List<CompanyDTO> companyModelToDTO(List<Company> companies) {
		return companies.parallelStream().map(CompanyMapper::companyModelToDTO)
				.collect(Collectors.toList());
	}

	public static Company companyDTOToModel(CompanyDTO dto) {
		if (dto == null) {
			return null;
		}
		return Company.build().id(dto.getId()).name(dto.getName()).create();
	}

	public static List<Company> companyDTOToModel(List<CompanyDTO> companies) {
		return companies.parallelStream().map(CompanyMapper::companyDTOToModel)
				.collect(Collectors.toList());
	}

}
