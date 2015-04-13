package com.excilys.formation.cdb.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is the object version of a Computer from the Database.
 *
 * @author Joxit
 */
public class Computer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Long companyId;

	/**
	 * Initialize a computer
	 */
	public Computer() {

	}

	/**
	 * @param name
	 *            of the computer
	 * @param introduced
	 *            when the computer was introduced
	 * @param discontinued
	 *            when the computer was discontinued
	 * @param company_id
	 *            of the computer
	 */
	public Computer(String name, Date introduced, Date discontinued,
			Long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	/**
	 * @param id
	 *            of the computer
	 * @param name
	 *            of the computer
	 * @param introduced
	 *            when the computer was introduced
	 * @param discontinued
	 *            when the computer was discontinued
	 * @param company_id
	 *            of the computer
	 */
	public Computer(Long id, String name, Date introduced, Date discontinued,
			Long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	/**
	 * @return id of the computer
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            of the computer
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return name of the computer
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            of the computer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return when the computer was introduced
	 */
	public Date getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced
	 *            when the computer was introduced
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return when the computer was discontinued
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued
	 *            when the computer was discontinued
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return company id of the computer
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            of the computer
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = (prime * result)
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result)
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Computer)) {
			return false;
		}
		Computer other = (Computer) obj;
		if (companyId == null) {
			if (other.companyId != null) {
				return false;
			}
		} else if (!companyId.equals(other.companyId)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
