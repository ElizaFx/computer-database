package com.excilys.formation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/test-application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Utils {

	public static ApplicationContext context = new ClassPathXmlApplicationContext(
			"test-application-context.xml");

	@Autowired
	private static JdbcTemplate jdbcTemplate = context
			.getBean(JdbcTemplate.class);

	public Utils() {

	}

	public void loadDatabase() throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(Utils.class
				.getClassLoader().getResourceAsStream("db/3-ENTRIES.sql"),
				"UTF-8"));
		f.lines().forEach(l -> {
			if (!l.isEmpty()) {
				jdbcTemplate.execute(l);
			}
		});
		f.close();
	}

	public void unloadDatabase() {
		jdbcTemplate.execute("delete from computer");
		jdbcTemplate.execute("delete from company");
	}
}
