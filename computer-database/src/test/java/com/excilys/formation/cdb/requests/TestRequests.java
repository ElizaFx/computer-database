package com.excilys.formation.cdb.requests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.Utils;
import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.ui.requests.CatRequest;
import com.excilys.formation.cdb.ui.requests.LSRequest;
import com.excilys.formation.cdb.ui.requests.MKRequest;
import com.excilys.formation.cdb.ui.requests.MVRequest;
import com.excilys.formation.cdb.ui.requests.RMRequest;
import com.excilys.formation.cdb.ui.requests.Request;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRequests {

	@Autowired
	private ComputerService computerService;

	@BeforeClass
	public static void setUp() throws IOException {
		new Utils().loadDatabase();
	}

	@AfterClass
	public static void reset() throws IOException {
		new Utils().unloadDatabase();
	}

	@Test
	public void ListComputerTest() {
		String cmd = LSRequest.CMD + " " + LSRequest.LIST_COMPUTER;
		try {
			ICommand command;
			command = new Request(cmd).processCommand();
			command.execute();
		} catch (RequestNotFoundException e) {
			fail(cmd + " should be a correct Command! : " + e.getMessage());
		}
	}

	@Test
	public void ListCompanyTest() {
		String cmd = LSRequest.CMD + " " + LSRequest.LIST_COMPANIES;
		try {
			ICommand command;
			command = new Request(cmd).processCommand();
			command.execute();
		} catch (RequestNotFoundException e) {
			fail(cmd + " should be a correct Command! : " + e.getMessage());
		}
	}

	@Test
	public void ShowComputerTest() {
		String cmd = CatRequest.CMD + " 20";
		try {
			ICommand command;
			command = new Request(cmd).processCommand();
			command.execute();
		} catch (RequestNotFoundException e) {
			fail(cmd + " should be a correct Command! : " + e.getMessage());
		}
	}

	@Test
	public void CreateUpdateDeleteComputerTest() {
		String sCmd1 = MKRequest.CMD + " " + MKRequest.NAME + " Joxit "
				+ MKRequest.COMPANY_ID + " 17";

		try {
			ICommand cmd;
			cmd = new Request(sCmd1).processCommand();
			cmd.execute();
		} catch (RequestNotFoundException e) {
			fail(sCmd1 + " should be a correct Command! : " + e.getMessage());
		}
		Computer computer = computerService.find(c -> (c.getName() != null)
				&& c.getName().equals("Joxit"));
		assertNotNull(computer);

		String sCmd2 = MVRequest.CMD + " " + computer.getId() + " "
				+ MVRequest.INTRODUCED + " 17-02-1993 " + MVRequest.NAME
				+ "\"Joxit 42\"";
		try {
			ICommand cmd2;
			cmd2 = new Request(sCmd2).processCommand();
			cmd2.execute();
		} catch (RequestNotFoundException e) {
			fail(sCmd2 + " should be a correct Command! : " + e.getMessage());
		}
		assertNotNull(computerService.find(c -> c.getName().equals("Joxit 42")));
		assertNull(computerService.find(c -> c.getName().equals("Joxit")));

		String sCmd3 = RMRequest.CMD + " " + RMRequest.RM_COMPUTER + " "
				+ computer.getId();
		try {
			ICommand cmd3;
			cmd3 = new Request(sCmd3).processCommand();
			cmd3.execute();
		} catch (RequestNotFoundException e) {
			fail(sCmd3 + " should be a correct Command! : " + e.getMessage());
		}

		assertNull(computerService.find(computer.getId()));
	}

	@Test
	public void invalidRequests() {
		try {
			ICommand command;
			command = new Request("").processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {
		}
		try {
			ICommand command;
			command = new Request("sqfsdf").processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {
		}
	}

	@Test(expected = RequestNotFoundException.class)
	public void invalidCatRequestBadCmd() throws RequestNotFoundException {
		ICommand command;
		command = new Request(CatRequest.CMD + " bad command man")
				.processCommand();
		command.execute();
	}

	@Test(expected = RequestNotFoundException.class)
	public void invalidCatRequestNegativeCmd() throws RequestNotFoundException {

		ICommand command;
		command = new Request(CatRequest.CMD + " -9").processCommand();
		command.execute();
	}

	@Test
	public void invalidCatRequestOutOfBoundCmd()
			throws RequestNotFoundException {
		ICommand command;
		command = new Request(CatRequest.CMD + " 10007").processCommand();
		command.execute();
	}

	@Test(expected = RequestNotFoundException.class)
	public void invalidRMRequestBadCommand() throws RequestNotFoundException {
		ICommand command;
		command = new Request(RMRequest.CMD + " bad command man")
				.processCommand();
		command.execute();
	}

	@Test(expected = RequestNotFoundException.class)
	public void invalidRMRequestNegativeCmd() throws RequestNotFoundException {
		ICommand command;
		command = new Request(RMRequest.CMD + " -17").processCommand();
		command.execute();
	}

	@Test
	public void invalidRMRequestOutOfBoundCmd() throws RequestNotFoundException {
		ICommand command;
		command = new Request(RMRequest.CMD + " " + RMRequest.RM_COMPUTER
				+ " 10007").processCommand();
		command.execute();

	}

	@Test(expected = RequestNotFoundException.class)
	public void invalidLSRequests() throws RequestNotFoundException {
		ICommand command;
		command = new Request(LSRequest.CMD + " bad command man")
				.processCommand();
		command.execute();
	}

	@Test
	public void invalidMVRequests() {
		try {
			ICommand command;
			command = new Request(MVRequest.CMD + " bad command man")
					.processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {

		}
		try {
			ICommand command;
			command = new Request(MVRequest.CMD + " 10000").processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {

		}
		try {
			ICommand command;
			command = new Request(MVRequest.CMD + " 10 " + MVRequest.INTRODUCED
					+ " 4564/54/677").processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {

		}
		try {
			ICommand command;
			command = new Request(MVRequest.CMD + " 10 " + MVRequest.COMPANY_ID
					+ " 4564/54/677").processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {

		}
		try {
			ICommand command;
			command = new Request(MVRequest.CMD + " 10 " + MVRequest.COMPANY_ID
					+ " " + MVRequest.INTRODUCED).processCommand();
			command.execute();
			fail("Should be a bad command");
		} catch (RequestNotFoundException e) {

		}
	}
}
