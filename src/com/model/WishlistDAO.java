package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public WishlistDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}

	public int addToWishList(int user_id, int book_id) {
		// Return 0 = book already in wish list, 1 = book added, -1 = error
		// adding

		String sql1 = "SELECT book_id FROM wishlist WHERE user_id = " + user_id
				+ " AND book_id = " + book_id + "";
		String sql2 = "SELECT seller_id FROM books WHERE book_id = " + book_id
				+ "";

		PreparedStatement pStatement = null;
		ResultSet result;
		try {

			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			result = pStatement.executeQuery();

			if (result.next())
				return 0;
			// System.out.println("Max qty : " + maxQty);
			else {
				int seller_id = 0;

				pStatement = (PreparedStatement) conn.prepareStatement(sql2);
				result = pStatement.executeQuery();
				if (result.next())
					seller_id = result.getInt(1);
				// seller_id = 2;
				// System.out.println("I am here 3");
				String sql3 = "INSERT INTO wishlist(user_id, book_id, seller_id) VALUES("
						+ user_id + "," + book_id + "," + seller_id + ")";
				pStatement = (PreparedStatement) conn.prepareStatement(sql3);
				pStatement.executeUpdate();
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return -1;
		}
	}
	
	public static List<WishList> getWishList() {
		List<WishList> wishlist_list = new ArrayList<WishList>();
		/*
		 * String sql =
		 * "select book_id, seller_id, quantity from cart_details where user_id = "
		 * + AuthDAO.user_id;
		 */

		String sql = "SELECT title, first_name, middle_name, last_name, price, books.book_id, wishlist_id, wishlist.seller_id "
				+ "FROM books, user_profile, wishlist where books.book_id = wishlist.book_id "
				+ "AND wishlist.seller_id = user_profile.user_id and wishlist.user_id = "
				+ AuthDAO.user_id;

//		System.out.println("SQL : " + sql);
		ResultSet result;
		try {
			PreparedStatement pStatement =  conn.prepareStatement(sql);
			System.out.println("I am here 1");
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++) {
				WishList wishlist = new WishList();
				wishlist.setBookTitle(result.getString(1));
				wishlist.setSellerName(result.getString(2) + " "
						+ result.getString(3) + " " + result.getString(4));
				wishlist.setPrice(result.getDouble(5));
				wishlist.setBook_id(result.getInt(6));
				wishlist.setWishlist_id(result.getInt(7));
				wishlist.setSeller_id(result.getInt(8));				
				// System.out.println("Count : " + total_quantity);
				wishlist_list.add(i, wishlist);
				// System.out.println("Size : " + cart_arraylist.size());
			}
			return wishlist_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}
	
	public static boolean deleteItemFromWishList(int book_id) {

		String sql = "DELETE from wishlist where user_id = "
				+ AuthDAO.user_id + " AND book_id = " + book_id;
		// ResultSet result;
		PreparedStatement pStatement = null;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			pStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
	}
	
	public List<Integer> getUserIdbyBookId(int bookId) {

		String sql = "select user_id from wishlist where book_id = "
				+ bookId;
		List<Integer> userIdList = new ArrayList<Integer>();
		PreparedStatement pStatement = null;
		ResultSet result;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			while (result.next()) {
				userIdList.add(result.getInt("user_id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userIdList;
	}

}
