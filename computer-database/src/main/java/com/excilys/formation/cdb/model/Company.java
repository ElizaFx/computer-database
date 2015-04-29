package com.excilys.formation.cdb.model;

import java.io.Serializable;

/**
 * This class is the object version of a Company from the Database.
 *
 * @author Joxit
 */
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	/**
	 * Initialize a company
	 */
	public Company() {
	}

	/**
	 * @param name
	 *            of the company
	 */
	public Company(String name) {
		this.name = name;
	}

	/**
	 * @param id
	 *            of the company
	 * @param name
	 *            of the company
	 */
	public Company(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return id of the company
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            of the company
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return name of the company
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            of the company
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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

	public final static CompanyBuilder build() {
		return new CompanyBuilder();
	}

	public final static class CompanyBuilder {
		private Company company;

		public CompanyBuilder() {
			company = new Company();
		}

		public CompanyBuilder name(String name) {
			company.setName(name);
			return this;
		}

		public CompanyBuilder id(Long id) {
			company.setId(id);
			return this;
		}

		public Company create() {
			return company;
		}

	}
}
