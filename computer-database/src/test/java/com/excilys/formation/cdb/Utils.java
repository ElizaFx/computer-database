package com.excilys.formation.cdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Utils {

	public static ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Autowired
	private static ConnectionFactory connectionFactory = context
			.getBean(ConnectionFactory.class);

	public void loadDatabase() throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(Utils.class
				.getClassLoader().getResourceAsStream("db/3-ENTRIES.sql"),
				"UTF-8"));

		try {
			Connection c = connectionFactory.getConnection();
			final Statement s = c.createStatement();
			f.lines().forEach(l -> {
				try {
					if (!l.isEmpty()) {
						s.execute(l);
					}
				} catch (SQLException e) {
					System.out.println(l);
					throw new RuntimeException(l + e.getMessage(), e);
				}
			});
			connectionFactory.close(c, s, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		f.close();
	}

	public void unloadDatabase() {
		try {
			Connection c = connectionFactory.getConnection();
			final Statement s = c.createStatement();
			s.execute("delete from computer");
			s.execute("delete from company");
			connectionFactory.close(c, s, null);
		} catch (SQLException e) {
		}
	}
}
