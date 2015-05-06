package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.exception.DAOException;
import com.excilys.formation.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company> {
	/**
	 * @param result
	 * @return result translated in the model
	 */
	public static Company toModel(ResultSet result) {
		try {
			return new CompanyMapper().mapRow(result, 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public static CompanyDTO toDTO(Company company) {
		return CompanyDTO.build().id(company.getId()).name(company.getName())
				.create();
	}

	public static List<CompanyDTO> toDTO(List<Company> companies) {
		return companies.parallelStream().map(CompanyMapper::toDTO)
				.collect(Collectors.toList());
	}

	public static Company toModel(CompanyDTO dto) {
		return Company.build().id(dto.getId()).name(dto.getName()).create();
	}

	public static List<Company> toModel(List<CompanyDTO> companies) {
		return companies.parallelStream().map(CompanyMapper::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Company.build().id(rs.getLong("company.id"))
				.name(rs.getString("company.name")).create();
	}

}
