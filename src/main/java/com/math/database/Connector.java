package com.math.database;

import java.sql.*;

public class Connector {
	private static Connector instance;
	private Connection connection;
	private Connector() {
		createConnection();
	}
	public static Connector getInstance() {
		if (instance == null) {
			instance = new Connector();
		}
		return instance;
	}
	public Connection getConnection() {
		if (connection == null) {
			createConnection();
		}
		return connection;
	}
	
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mathdb", "root", "root");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
