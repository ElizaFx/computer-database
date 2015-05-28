package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.service.ICompanyService;

@Path("json/company")
@Controller
public class CompanyJSON {

	@Autowired
	private ICompanyService companyService;

	@GET
	@Path("findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CompanyDTO> findAll() {
		return CompanyMapper.toDTO(companyService.findAll());
	}

	@GET
	@Path("find/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyDTO find(@PathParam("id") Long id) {
		return CompanyMapper.toDTO(companyService.find(id));
	}

	@DELETE
	@Path("remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(Long id) {
		companyService.remove(id);
	}
}
