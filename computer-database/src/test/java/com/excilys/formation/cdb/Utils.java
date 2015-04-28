package com.excilys.formation.cdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.formation.cdb.persistence.connection.ConnectionFactory;

public class Utils {
	public static void loadDatabase() throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(Utils.class
				.getClassLoader().getResourceAsStream("db/3-ENTRIES.sql"),
				"UTF-8"));

		try {
			final Statement s = ConnectionFactory.getConnection()
					.createStatement();
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
			ConnectionFactory.closeConnection(s, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		f.close();
	}

	public static void unloadDatabase() {
		try {
			final Statement s = ConnectionFactory.getConnection()
					.createStatement();
			s.execute("delete from computer");
			s.execute("delete from company");
			ConnectionFactory.closeConnection(s, null);
		} catch (SQLException e) {
		}
	}
}
