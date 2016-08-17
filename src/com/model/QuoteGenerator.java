package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class QuoteGenerator {

	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public QuoteGenerator() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	
	public static String generateQuote() {
		Random randomGenerator = new Random();
		String sql = "select quote from quotes where quote_no = " + randomGenerator.nextInt(30);
		PreparedStatement pStatement = null;
		ResultSet result = null;

		try {
			pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			while (result.next()) {
				return result.getString("quote");
			}

			pStatement.close();

		} catch (SQLException e) {
			return "Books serve to show a man that those original thoughts of his aren’t very new after all. –Abraham Lincoln";
		}
		return "Books serve to show a man that those original thoughts of his aren’t very new after all. –Abraham Lincoln";
	}
}
