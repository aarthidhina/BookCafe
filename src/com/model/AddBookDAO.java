package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBookDAO {

	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public AddBookDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}

	public boolean addBook(int seller_id, String title, String author,
			String isbn, String dept, String description, double price,
			double shippingCost, int quantity, String image) {

		String insertSql = "INSERT INTO `books` (`seller_id`, `title`, `author`, `isbn`, `price`, `shipping_cost`, `description`, `image`, `department`, `quantity`) VALUES ("
				+ seller_id
				+ ",'"
				+ title
				+ "','"
				+ author
				+ "','"
				+ isbn
				+ "',"
				+ price
				+ ","
				+ shippingCost
				+ ",'"
				+ description
				+ "','" + image + "','" + dept + "', " + quantity + ")";

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(insertSql);

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;

	}

	public boolean updateBookQuantity(int book_id, int quantity) {
		String sql = "update books set quantity = " + quantity
				+ " where book_id = " + book_id;
//		System.out.println("SQL : " + sql);
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;
	}

	public int getBookQuantity(int book_id) {
		String sql = "select quantity from books where book_id = " + book_id;
		int quantity = 0;
//		System.out.println("SQL : " + sql);
		ResultSet result;
		PreparedStatement pStatement = null;
		//System.out.println("SQL : " + sql);
		try {
			pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();

			if (result.next()) {
				quantity = result.getInt("quantity");
				return quantity;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println("Stack trace");
			e.printStackTrace();
			return -1;
		}

		return quantity;
	}
	
	public Books getBookDetail(int book_id) {
		String sql = "select title, author, image from books where book_id = " + book_id;
//		System.out.println("inside getBookDetail");
//		System.out.println("sql " + sql);
		ResultSet result;
		PreparedStatement pStatement = null;
		Books book = new Books();
		try {
			pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			
			if(result.next()) {
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setImage(result.getString("image"));
			}
//			System.out.println(book.getTitle());
//			System.out.println(book.getAuthor());
//			System.out.println(book.getImage());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
//	public static void main(String a[]) {
//		AddBookDAO dao = new AddBookDAO();
//		dao.getBookQuantity(1);
//		dao.updateBookQuantity(1, 3);
//	}

}
