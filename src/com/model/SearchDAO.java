package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public SearchDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}

	public static List<Books> searchBook(String title, String author,
			String isbn, String dept) {

		String sql = "SELECT * FROM books, user_profile WHERE ";
		ResultSet result;
		ArrayList<Books> books_arraylist = new ArrayList<Books>();
		String whereString = "";
		int iWhrMultiple = 0;
		if (!(title.isEmpty())) {
			whereString = whereString + "title like '%" + title + "%'";
			iWhrMultiple = 1;
		}
		if (!(author.isEmpty())) {
			if (iWhrMultiple == 1)
				whereString = whereString + " AND ";
			whereString = whereString + "author like '%" + author + "%'";
			iWhrMultiple = 1;
		}
		if (!(isbn.isEmpty())) {
			if (iWhrMultiple == 1)
				whereString = whereString + " AND ";
			whereString = whereString + "isbn like '%" + isbn + "%'";
			iWhrMultiple = 1;
		}
		if (!(dept.isEmpty())) {
			if (iWhrMultiple == 1)
				whereString = whereString + " AND ";
			whereString = whereString + "department like '%" + dept + "%'";
			iWhrMultiple = 1;
		}
		sql = sql + whereString +" AND user_profile.user_id=books.seller_id";
		try {
			// System.out.println("SQL : "+sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

			for (int i = 0; result.next(); i++) {
				// System.out.println("Title : " + result.getString("title"));

				Books book = new Books();

				book.setBook_id(result.getString("book_id"));
				book.setSeller_id(result.getString("seller_id"));
				book.setSellerEmail(result.getString("email"));
				book.setPrice(result.getDouble("price"));
				book.setShipping_cost(result.getDouble("shipping_cost"));
				book.setDescription(result.getString("description"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setIsbn(result.getString("isbn"));
				book.setImage(result.getString("image"));
				book.setDepartment(result.getString("department"));
				book.setSeller_name(result.getString("first_name") + " "
						+ result.getString("middle_name") + " "
						+ result.getString("last_name"));
				books_arraylist.add(i, book);
				// System.out.println("Result " + i + " : "
				// + books_arraylist.get(i).getTitle());
				// System.out.println("Result 0 : "
				// + books_arraylist.get(0).getTitle());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// for (int i = 0; i < books_arraylist.size(); i++) {
		// System.out.println("Result " + i + " : "
		// + books_arraylist.get(i).getTitle());
		// }
		return books_arraylist;
	}

	public List<Books> getBooksBySellerId(int sellerId) {
		String sql = "SELECT * FROM books WHERE seller_id=" + sellerId + ";";
		ResultSet result;
		ArrayList<Books> books_arraylist = new ArrayList<Books>();

		try {
			// System.out.println("SQL : "+sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

			for (int i = 0; result.next(); i++) {
				// System.out.println("Title : " + result.getString("title"));

				Books book = new Books();

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

				books_arraylist.add(i, book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return books_arraylist;
	}

	public static void DB_Close() throws Throwable {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// public static void main(String[] a)
	// {
	// searchBook("Book Title","Book author","Book ISBN", "Book dept");
	// }
}
