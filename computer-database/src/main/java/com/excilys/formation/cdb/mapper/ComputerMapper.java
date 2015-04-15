package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.DAOException;

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
			computer.setIntroduced(result.getTimestamp("computer.introduced"));
			computer.setDiscontinued(result
					.getTimestamp("computer.discontinued"));
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
}
