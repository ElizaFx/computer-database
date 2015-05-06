package com.excilys.formation.cdb.dto;

public class ComputerDTO {
	private Long id;
	private String name;
	private String introduced;
	private String discontinued;
	private Long companyId;
	private String companyName;

	public ComputerDTO() {
	}

	public ComputerDTO(Long id, String name, String introduced,
			String discontinued, Long companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public final static ComputerDTOBuilder build() {
		ComputerDTOBuilder res = new ComputerDTOBuilder();
		return res;
	}

	public final static class ComputerDTOBuilder {
		private ComputerDTO computer;

		private ComputerDTOBuilder() {
			computer = new ComputerDTO();
		}

		public ComputerDTOBuilder id(Long id) {
			computer.setId(id);
			return this;
		}

		public ComputerDTOBuilder name(String name) {
			computer.setName(name);
			return this;
		}

		public ComputerDTOBuilder introduced(String introduced) {
			computer.setIntroduced(introduced);
			return this;
		}

		public ComputerDTOBuilder discontinued(String discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}

		public ComputerDTOBuilder companyId(Long companyId) {
			computer.setCompanyId(companyId);
			return this;
		}

		public ComputerDTOBuilder companyName(String companyName) {
			computer.setCompanyName(companyName);
			return this;
		}

		public ComputerDTO create() {
			return computer;
		}
	}
}
