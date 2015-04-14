package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.util.Util;

/**
 *
 * @author Joxit
 */
public class ComputerDAO extends AbstractDAO<Computer> {

	private final static ComputerDAO _instance = new ComputerDAO();

	private ComputerDAO() {
		super(Computer.class);
	}

	@Override
	protected Computer getModel(ResultSet result) {
		Computer computer = new Computer();
		try {
			computer.setId(result.getLong("id"));
			computer.setName(result.getString("name"));
			computer.setIntroduced(result.getTimestamp("introduced"));
			computer.setDiscontinued(result.getTimestamp("discontinued"));
			computer.setCompanyId(result.getLong("company_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public Computer find(Object id) {
		return super.find(c -> c.getId().equals(id));
	}

	@Override
	public int insert(Computer model) {
		StringBuilder request = new StringBuilder();
		boolean first = true;
		if (model.getName() != null) {
			request.append("name='");
			request.append(model.getName());
			request.append('\'');
			first = false;
		}
		if (model.getIntroduced() != null) {
			if (!first) {
				request.append(", ");
			}
			request.append("introduced='");
			request.append(Util.formatDate(model.getIntroduced()));
			request.append('\'');
			first = false;
		}
		if (model.getDiscontinued() != null) {
			if (!first) {
				request.append(", ");
			}
			request.append("discontinued='");
			request.append(Util.formatDate(model.getDiscontinued()));
			request.append('\'');
			first = false;
		}
		if ((model.getCompanyId() != null) && (model.getCompanyId() != 0)) {
			if (!first) {
				request.append(", ");
			}
			request.append("company_id=");
			request.append(model.getCompanyId());
		}
		return super.insertRequest(request.toString());
	}

	@Override
	public int remove(Computer model) {
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
			first = false;
		}
		if (model.getIntroduced() != null) {
			if (!first) {
				request.append(" and ");
			}
			request.append("introduced='");
			request.append(Util.formatDate(model.getIntroduced()));
			request.append('\'');
			first = false;
		}
		if (model.getDiscontinued() != null) {
			if (!first) {
				request.append(" and ");
			}
			request.append("discontinued='");
			request.append(Util.formatDate(model.getDiscontinued()));
			request.append('\'');
			first = false;
		}
		if ((model.getCompanyId() != null) && (model.getCompanyId() != 0)) {
			if (!first) {
				request.append(" and ");
			}
			request.append("company_id=");
			request.append(model.getCompanyId());
		}
		return super.removeRequest(request.toString());

	}

	@Override
	public int update(Computer model) {
		StringBuilder request = new StringBuilder();
		boolean first = true;
		if (model.getName() != null) {
			if (!first) {
				request.append(" , ");
			}
			request.append("name='");
			request.append(model.getName());
			request.append('\'');
			first = false;
		}
		if (model.getIntroduced() != null) {
			if (!first) {
				request.append(" , ");
			}
			request.append("introduced='");
			request.append(Util.formatDate(model.getIntroduced()));
			request.append('\'');
			first = false;
		}
		if (model.getDiscontinued() != null) {
			if (!first) {
				request.append(" , ");
			}
			request.append("discontinued='");
			request.append(Util.formatDate(model.getDiscontinued()));
			request.append('\'');
			first = false;
		}
		if ((model.getCompanyId() != null) && (model.getCompanyId() != 0)) {
			if (!first) {
				request.append(" , ");
			}
			request.append("company_id=");
			request.append(model.getCompanyId());
		}
		System.out.println(request.toString());
		return super.updateRequest("id=" + model.getId(), request.toString());
	}

	public static ComputerDAO getInstance() {
		return _instance;
	}
}
