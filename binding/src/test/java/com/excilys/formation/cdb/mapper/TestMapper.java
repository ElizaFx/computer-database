package com.excilys.formation.cdb.mapper;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;

public class TestMapper {

	@Autowired
	private ComputerMapper computerMapper;

	@Test(expected = NullPointerException.class)
	public void invalidCompanyModel() {
		assertNull(CompanyMapper.toModel((CompanyDTO) null));
	}

	@Test(expected = NullPointerException.class)
	public void invalidComputerModel() {
		assertNull(computerMapper.toModel((ComputerDTO) null));
	}
}
