package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.service.ICompanyService;

@RestController
@RequestMapping("/json/company")
public class CompanyJSON {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<CompanyDTO> findAll() {
		return CompanyMapper.toDTO(companyService.findAll());
	}

	@RequestMapping(value = "find/{id:[0-9]+}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyDTO find(@PathParam("id") Long id) {
		return CompanyMapper.toDTO(companyService.find(id));
	}

	@RequestMapping(value = "remove/{id:[0-9]+}", method = RequestMethod.DELETE)
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(Long id) {
		companyService.remove(id);
	}
}
