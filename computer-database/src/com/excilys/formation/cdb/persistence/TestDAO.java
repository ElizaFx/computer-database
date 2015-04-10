package com.excilys.formation.cdb.persistence;

import org.junit.Test;

import com.excilys.formation.cdb.model.Computer;

/**
 * 
 * @author Joxit
 */
public class TestDAO {

	@Test
	public void findAllTest() {
		ComputerDAO crf = new ComputerDAO();
		CompanyDAO cyf = new CompanyDAO();
		System.out.println(crf.findAll());
		System.out.println(cyf.findAll());
	}

	@Test
	public void findTest() {
		ComputerDAO crf = new ComputerDAO();
		CompanyDAO cyf = new CompanyDAO();

		System.out.println(crf.find(20l));
		System.out.println(cyf.find(20l));

		assert (crf.find(-1l) == null);
		assert (cyf.find(2000l) == null);
	}

	@Test
	public void createUpdateRemoveTest() {
		ComputerDAO crf = new ComputerDAO();
		crf.insert(new Computer("Joxit", null, null, 20l));
		crf.findAll().stream().filter(c -> c.getName().startsWith("Joxit"))
				.forEach(c -> System.out.println(c));
		Computer jox = crf.find(c -> c.getName().equals("Joxit"));
		assert (jox != null);
		System.out.println("jox1 " + jox);
		assert (crf.find(c -> c.getName().equals("Joxit42")) == null);
		jox.setName("Joxit42");
		crf.update(jox);
		assert (crf.find(c -> c.equals(jox)) != null);
		crf.remove(jox);
		assert (crf.find(c -> c.equals(jox)) == null);
		assert (crf.find(c -> c.getName().equals("Joxit")) == null);
	}
}
