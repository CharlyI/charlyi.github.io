package com.math.database.Init;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

import com.math.database.Connector;

public class InitDB {
	public void init() {
		try {
			
			Connection con = Connector.getInstance().getConnection();
			String path = "E:/programs/Java EE/test/src/main/SQL/Creating_mathDB.sql";
			List<String> encoded = Files.readAllLines(Paths.get(path));
			String query = "";
			for(String row : encoded) {
				query = query.concat(row);
			}
			System.out.println(query);
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
