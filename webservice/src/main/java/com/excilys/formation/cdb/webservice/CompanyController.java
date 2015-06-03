package com.excilys.formation.cdb.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.service.ICompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyDTO> findAll() {
		return CompanyMapper.toDTO(companyService.findAll());
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.GET)
	public CompanyDTO find(@PathVariable("id") Long id) {
		return CompanyMapper.toDTO(companyService.find(id));
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.DELETE)
	public void remove(@PathVariable("id") Long id) {
		companyService.remove(id);
	}
}
