package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;

@RestController
@RequestMapping("/json/computer")
public class ComputerJSON {

	@Autowired
	private IComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;

	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> findAll() {
		return computerMapper.toDTO(computerService.findAll());
	}

	@RequestMapping(value = "find/{id:[0-9]+}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO find(@PathVariable("id") Long id) {
		return computerMapper.toDTO(computerService.find(id));
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO create(@RequestBody ComputerDTO computer) {
		Computer c = computerMapper.toModel(computer);
		computerService.insert(c);
		return computerMapper.toDTO(c);
	}

	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO edit(@RequestBody ComputerDTO computer) {
		Computer c = computerMapper.toModel(computer);
		computerService.update(c);
		return computerMapper.toDTO(c);
	}

	@RequestMapping(value = "remove/{id:[0-9]+}", method = RequestMethod.DELETE)
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(@PathVariable("id") Long id) {
		computerService.remove(computerService.find(id));
	}
}
