package com.excilys.formation.cdb.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.service.IComputerService;

@RestController
@RequestMapping("/computers")
public class ComputerController {

	@Autowired
	private IComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;

	@RequestMapping(method = RequestMethod.GET)
	public List<ComputerDTO> findAll() {
		return computerService.findAll();
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.GET)
	public ComputerDTO find(@PathVariable("id") Long id) {
		return computerService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ComputerDTO create(@RequestBody ComputerDTO computer) {
		computerService.insert(computer);
		return computer;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ComputerDTO edit(@RequestBody ComputerDTO computer) {
		computerService.update(computer);
		return computer;
	}

	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.DELETE)
	public void remove(@PathVariable("id") Long id) {
		computerService.remove(computerService.find(id));
	}
}
