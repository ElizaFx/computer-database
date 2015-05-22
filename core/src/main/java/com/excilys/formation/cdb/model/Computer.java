package com.excilys.formation.cdb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.formation.cdb.mapper.DateMapper;

/**
 * This class is the object version of a Computer from the Database.
 *
 * @author Joxit
 */
@Table(name = "computer")
@Entity
public class Computer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Convert(converter = DateMapper.class)
	private LocalDate introduced;
	@Convert(converter = DateMapper.class)
	private LocalDate discontinued;
	@ManyToOne
	private Company company;

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
	public Computer(String name, LocalDate introduced, LocalDate discontinued,
			Company companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		company = companyId;
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
	public Computer(Long id, String name, LocalDate introduced,
			LocalDate discontinued, Company companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		company = companyId;
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
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced
	 *            when the computer was introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return when the computer was discontinued
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued
	 *            when the computer was discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return company id of the computer
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param companyId
	 *            of the computer
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((company == null) ? 0 : company.hashCode());
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
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
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

	public final static ComputerBuilder build() {
		return new ComputerBuilder();
	}

	public final static class ComputerBuilder {
		private Computer computer;

		private ComputerBuilder() {
			computer = new Computer();
		}

		public ComputerBuilder id(Long id) {
			computer.setId(id);
			return this;
		}

		public ComputerBuilder name(String name) {
			computer.setName(name);
			return this;
		}

		public ComputerBuilder introduced(LocalDate introduced) {
			computer.setIntroduced(introduced);
			return this;
		}

		public ComputerBuilder discontinued(LocalDate discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}

		public ComputerBuilder company(Company company) {
			computer.setCompany(company);
			return this;
		}

		public Computer create() {
			return computer;
		}
	}
}
