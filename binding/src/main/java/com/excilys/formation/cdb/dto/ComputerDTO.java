package com.excilys.formation.cdb.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.formation.cdb.validation.DateField;

public class ComputerDTO {
	private Long id;
	@NotBlank
	private String name;
	@DateField
	private String introduced;
	@DateField
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

	@NotBlank
	public String getComputerName() {
		return name;
	}

	public void setName(@NotBlank String name) {
		this.name = name != null ? name.trim() : null;
	}

	public void setComputerName(@NotBlank String name) {
		this.name = name != null ? name.trim() : null;
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

	public final static ComputerDTOBuilder builder() {
		ComputerDTOBuilder res = new ComputerDTO.ComputerDTOBuilder();
		return res;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = (prime * result)
				+ ((companyName == null) ? 0 : companyName.hashCode());
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
		if (!(obj instanceof ComputerDTO)) {
			return false;
		}
		ComputerDTO other = (ComputerDTO) obj;
		if (companyId == null) {
			if (other.companyId != null) {
				return false;
			}
		} else if (!companyId.equals(other.companyId)) {
			return false;
		}
		if (companyName == null) {
			if (other.companyName != null) {
				return false;
			}
		} else if (!companyName.equals(other.companyName)) {
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

		public ComputerDTO build() {
			return computer;
		}
	}

}
