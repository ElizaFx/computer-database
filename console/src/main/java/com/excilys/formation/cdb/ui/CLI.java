package com.excilys.formation.cdb.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.formation.cdb.exception.RequestNotFoundException;
import com.excilys.formation.cdb.ui.cmd.ICommand;
import com.excilys.formation.cdb.ui.requests.Request;

public class CLI {
	private final List<ICommand> history;

	public CLI() {
		history = new ArrayList<ICommand>();
	}

	public static ApplicationContext context = new ClassPathXmlApplicationContext(
			"binding-application-context.xml");

	public static void main(String[] args) throws IOException {
		CLI cli = new CLI();
		cli.run();
	}

	public void run() throws IOException {
		while (true) {
			BufferedReader ir = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String request = ir.readLine();
				if (request != null) {
					switch (request) {
						case "exit":
							return;
						case "help":
							Request.help();
							break;
						default:
							ICommand command = new Request(request)
									.processCommand();
							history.add(command);
							command.execute();
							break;
					}
				}
			} catch (RequestNotFoundException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
