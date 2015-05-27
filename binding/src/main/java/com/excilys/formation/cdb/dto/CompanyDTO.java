package com.excilys.formation.cdb.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
public class CompanyDTO {
	private Long id;
	@NotBlank
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

	public final static CompanyDTOBuilder build() {
		return new CompanyDTOBuilder();
	}

	public final static class CompanyDTOBuilder {
		private CompanyDTO company;

		public CompanyDTOBuilder() {
			company = new CompanyDTO();
		}

		public CompanyDTOBuilder name(String name) {
			company.setName(name);
			return this;
		}

		public CompanyDTOBuilder id(Long id) {
			company.setId(id);
			return this;
		}

		public CompanyDTO create() {
			return company;
		}

	}
}
