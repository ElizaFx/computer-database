package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;

@Path("json/computer")
public class ComputerJSON {

	@Autowired
	private IComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;

	@GET
	@Path("findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> findAll() {
		return computerMapper.toDTO(computerService.findAll());
	}

	@GET
	@Path("find/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO find(@PathParam("id") Long id) {
		return computerMapper.toDTO(computerService.find(id));
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO create(ComputerDTO computer) {
		Computer c = computerMapper.toModel(computer);
		computerService.insert(c);
		return computerMapper.toDTO(c);
	}

	@PUT
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO edit(ComputerDTO computer) {
		Computer c = computerMapper.toModel(computer);
		computerService.update(c);
		return computerMapper.toDTO(c);
	}

	@DELETE
	@Path("remove/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		computerService.remove(computerService.find(id));
	}
}
