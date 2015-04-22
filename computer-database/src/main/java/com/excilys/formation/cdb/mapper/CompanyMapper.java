package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		return new CompanyDTO(company.getId(), company.getName());
	}

	public static List<CompanyDTO> companyModelToDTO(List<Company> companies) {
		List<CompanyDTO> res = new ArrayList<CompanyDTO>();
		companies.forEach(c -> res.add(companyModelToDTO(c)));
		return res;
	}

	public static Company companyDTOToModel(CompanyDTO dto) {
		if (dto == null) {
			return null;
		}
		Company res = new Company();
		res.setId(dto.getId());
		res.setName(dto.getName());
		return res;
	}

	public static List<Company> companyDTOToModel(List<CompanyDTO> companies) {
		List<Company> res = new ArrayList<Company>();
		companies.forEach(c -> res.add(companyDTOToModel(c)));
		return res;
	}

}
