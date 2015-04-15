package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.DAOException;

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
}
