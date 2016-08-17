package com.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DeleteUser {
	public static DatabaseConnection dc = null;
	private static Connection conn = null;
	
	public DeleteUser() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	public static boolean deleteuser(int user_id) {

		String insertSql = "update user set active='false' where user_id='"
				+ user_id + "'";
		
//		System.out.println("SQL in deleteuser = " + insertSql);
		//PreparedStatement pStatement = null;

		try {

			//ps = conn.prepareStatement();
			PreparedStatement	pStatement = (PreparedStatement) conn.prepareStatement(insertSql);
			if (pStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;

	}
	
	public static boolean activateAccount(int user_id) {

		String insertSql = "update user set active='true' where user_id='"
				+ user_id + "'";
		
//		System.out.println("SQL in deleteuser = " + insertSql);
		//PreparedStatement pStatement = null;

		try {

			//ps = conn.prepareStatement();
			PreparedStatement	pStatement = (PreparedStatement) conn.prepareStatement(insertSql);
			if (pStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;

	}
	public static boolean approveuser(int user_id) {

		String insertSql = "update approval set status='APPROVED' where seller_id='"
				+ user_id + "'";
		
//		System.out.println("SQL in deleteuser = " + insertSql);
		//PreparedStatement pStatement = null;

		try {

			//ps = conn.prepareStatement();
			PreparedStatement	pStatement = (PreparedStatement) conn.prepareStatement(insertSql);
			if (pStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;

	}
}
