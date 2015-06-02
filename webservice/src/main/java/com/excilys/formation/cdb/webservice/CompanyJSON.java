package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.service.ICompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyJSON {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyDTO> findAll() {
		return CompanyMapper.toDTO(companyService.findAll());
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.GET)
	public CompanyDTO find(@PathParam("id") Long id) {
		return CompanyMapper.toDTO(companyService.find(id));
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.DELETE)
	public void remove(Long id) {
		companyService.remove(id);
	}
}
