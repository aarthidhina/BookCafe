package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditBookDAO {

	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public EditBookDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	
	public Books getBookById(int bookId)
	{
		Books book = new Books();
		
		String selectSql = "SELECT * FROM books WHERE book_id=" + bookId + ";";
		ResultSet result;
		
		PreparedStatement ps = null;
		
		try {

			ps = conn.prepareStatement(selectSql);
			result = ps.executeQuery(selectSql);
			result.next();
			
			book.setBook_id(result.getString("book_id"));				
			book.setSeller_id(result.getString("seller_id"));
			book.setPrice(result.getDouble("price"));
			book.setShipping_cost(result.getDouble("shipping_cost"));
			book.setDescription(result.getString("description"));
			book.setTitle(result.getString("title"));
			book.setAuthor(result.getString("author"));
			book.setIsbn(result.getString("isbn"));
			book.setImage(result.getString("image"));
			book.setDepartment(result.getString("department"));
			book.setQuantity(result.getInt("quantity"));

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
		}
		
		return book;
	}
	
	
	public double getBookPrice(int book_id) {
		
		String sql = "select price from books where book_id = "
				+ book_id;

		ResultSet result = null;

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

			if (result.next()) {
				return result.getDouble("price");				
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
		return -1;		
	}
	
	public boolean editBook(int book_id, String title, String author,
			String isbn, String dept, String description, double price,
			double shippingCost, int quantity, String image)
	{
		String updateSql = "UPDATE books SET title='" + title + "', author='" + author	+ "', isbn='" + isbn
				+ "', price=" + price + ", shipping_cost=" + shippingCost	+ ", description='" + description
				+ "', image='" + image + "', department='" + dept + "', quantity=" + quantity
				+ " WHERE book_id=" + book_id;
		
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(updateSql);
			
			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;
	}
	
	public boolean deleteBook(int book_id)
	{
		String deleteSql = "DELETE FROM books WHERE book_id=" + book_id;
		
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(deleteSql);
			
			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}

		return false;
	}
}
