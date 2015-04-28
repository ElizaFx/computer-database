package com.excilys.formation.cdb.dto;


public class CompanyDTO {
	private Long id;
	private String name;

	/**
	 * Initialize a company
	 */
	public CompanyDTO() {
	}

	/**
	 * @param name
	 *            of the company
	 */
	public CompanyDTO(String name) {
		this.name = name;
	}

	/**
	 * @param id
	 *            of the company
	 * @param name
	 *            of the company
	 */
	public CompanyDTO(Long id, String name) {
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
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}

	public static CompanyBuilder build() {
		return new CompanyBuilder();
	}

	public static class CompanyBuilder {
		private CompanyDTO company;

		public CompanyBuilder() {
			company = new CompanyDTO();
		}

		public CompanyBuilder name(String name) {
			company.setName(name);
			return this;
		}

		public CompanyBuilder id(Long id) {
			company.setId(id);
			return this;
		}

		public CompanyDTO create() {
			return company;
		}

	}
}
