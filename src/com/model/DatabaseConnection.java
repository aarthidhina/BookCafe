package com.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	private static Connection conn = null;

	static final String DB_URL = "jdbc:mysql://localhost:3306/bookcafe";
	static final String DB_USER = "admin";
	static final String DB_PW = "password";
	static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	public Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			// System.out.println("DB Connection Successful");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;

	}

}
