package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.model.Company;

/**
 * 
 * @author Joxit
 */
public class CompanyDAO extends AbstractDAO<Company> {

	private final static CompanyDAO _instance = new CompanyDAO();

	private CompanyDAO() {
		super(Company.class);
	}

	@Override
	protected Company getModel(ResultSet result) {
		Company company = new Company();
		try {
			company.setId(result.getLong("id"));
		} catch (SQLException e) {
		}
		try {
			company.setName(result.getString("name"));
		} catch (SQLException e) {
		}
		return company;
	}

	@Override
	public Company find(Object id) {
		return super.find(c -> c.getId().equals(id));
	}

	@Override
	public int insert(Company model) {
		StringBuilder request = new StringBuilder();
		boolean first = true;
		if (model.getId() != null) {
			request.append("id=");
			request.append(model.getId());
			first = false;
		}
		if (model.getName() != null) {
			if (!first) {
				request.append(", ");
			}
			request.append("name='");
			request.append(model.getName());
			request.append('\'');
		}
		return 0;
		// super.insertRequest(request.toString());
	}

	@Override
	public int remove(Company model) {
		StringBuilder request = new StringBuilder();
		boolean first = true;
		if (model.getId() != null) {
			request.append("id=");
			request.append(model.getId());
			first = false;
		}
		if (model.getName() != null) {
			if (!first) {
				request.append(" and ");
			}
			request.append("name='");
			request.append(model.getName());
			request.append('\'');
		}
		return 0;
		// super.removeRequest(request.toString());
	}

	@Override
	public int update(Company model) {
		StringBuilder request = new StringBuilder();
		boolean first = true;
		if (model.getId() != null) {
			request.append("id=");
			request.append(model.getId());
			first = false;
		}
		if (model.getName() != null) {
			if (!first) {
				request.append(" and ");
			}
			request.append("name='");
			request.append(model.getName());
			request.append('\'');
		}
		return 0;
		// super.updateRequest("id=" + model.getId(), request.toString());
	}

	public static CompanyDAO getInstance() {
		return _instance;
	}
}
