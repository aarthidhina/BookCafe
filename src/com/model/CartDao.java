package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class CartDao {
	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public CartDao() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}

	@SuppressWarnings("resource")
	public static boolean addToCart(int user_id, int book_id, int quantity) {

		PreparedStatement pStatement = null;
		ResultSet result;
		try {
			String sql1 = "SELECT quantity FROM cart_details WHERE user_id = "
					+ user_id + " AND book_id = " + book_id + "";
			String sqlmax = "SELECT quantity FROM books WHERE book_id = "
					+ book_id;
			pStatement = (PreparedStatement) conn.prepareStatement(sqlmax);
			result = pStatement.executeQuery();
			int maxQty = 0;
			if (result.next())
				maxQty = result.getInt(1);
			// System.out.println("Max qty : " + maxQty);
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			result = pStatement.executeQuery();
			if (result.next()) {
				int qty = 0;
				qty = result.getInt(1);
				qty = qty + 1;
				if (qty <= maxQty) {
					String sql2 = "UPDATE cart_details SET quantity = " + qty
							+ " WHERE book_id = " + book_id;
					pStatement = (PreparedStatement) conn
							.prepareStatement(sql2);
					pStatement.executeUpdate();
					return true;
				} else {
					return false;
				}
			} else {
				int seller_id = 0;
				String sql3 = "SELECT seller_id FROM books WHERE book_id = "
						+ book_id + "";
				pStatement = (PreparedStatement) conn.prepareStatement(sql3);
				result = pStatement.executeQuery();
				if (result.next())
					seller_id = result.getInt(1);
				// seller_id = 2;
				// System.out.println("I am here 3");
				String sql4 = "INSERT INTO cart_details(user_id, book_id, seller_id, quantity) VALUES("
						+ user_id
						+ ","
						+ book_id
						+ ","
						+ seller_id
						+ ","
						+ quantity + ")";
				pStatement = (PreparedStatement) conn.prepareStatement(sql4);
				pStatement.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
	}

	public static int getCartTotal(int user_id) {

		String sql = "select sum(quantity) from cart_details where user_id = "
				+ user_id;
		int total_quantity = 0;
		ResultSet result;
		PreparedStatement pStatement = null;
		// System.out.println("Count : " + total_quantity);
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			if (result.next()) {
				total_quantity = result.getInt(1);
				// System.out.println("Count : " + total_quantity);
			}
			return total_quantity;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return 0;
		}
	}

	public static List<Cart> getCart() {
		ArrayList<Cart> cart_arraylist = new ArrayList<Cart>();
		/*
		 * String sql =
		 * "select book_id, seller_id, quantity from cart_details where user_id = "
		 * + AuthDAO.user_id;
		 */

		String sql = "SELECT title, first_name, middle_name, last_name, cart_details.quantity, price, books.book_id, cart_id, cart_details.seller_id, books.shipping_cost "
				+ "FROM books, user_profile, cart_details where books.book_id = cart_details.book_id "
				+ "AND cart_details.seller_id = user_profile.user_id and cart_details.user_id = "
				+ AuthDAO.user_id;

		// System.out.println("SQL : " + sql);
		ResultSet result;
		PreparedStatement pStatement = null;
		try {
			// System.out.println("I am here 1");
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++) {
				Cart cart = new Cart();
				cart.setBookTitle(result.getString(1));
				cart.setSellerName(result.getString(2) + " "
						+ result.getString(3) + " " + result.getString(4));
				cart.setQuantity(result.getInt(5));
				cart.setprice(result.getFloat(6));
				cart.setBook_id(result.getInt(7));
				cart.setCart_id(result.getInt(8));
				cart.setSeller_id(result.getInt(9));
				cart.setShippingCost(result.getInt(10));
				// System.out.println("Count : " + total_quantity);
				cart_arraylist.add(i, cart);
				// System.out.println("Size : " + cart_arraylist.size());
			}
			return cart_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}

	public static boolean deleteItemFromCart(int book_id) {

		String sql = "DELETE from cart_details where user_id = "
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

	public static boolean reduceCountInCart(int book_id) {
		String sql = "SELECT quantity FROM cart_details WHERE user_id = "
				+ AuthDAO.user_id + " AND book_id = " + book_id;
		ResultSet result;
		int quantity = 0;
		String sql2 = "";
		PreparedStatement pStatement = null;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			if (result.next())
				quantity = result.getInt(1);
			quantity = quantity - 1;
			if (quantity > 0) {
				sql2 = "UPDATE cart_details SET quantity = " + quantity
						+ " WHERE  user_id = " + AuthDAO.user_id
						+ " AND book_id = " + book_id;
			} else {
				sql2 = "DELETE FROM cart_details WHERE  user_id = "
						+ AuthDAO.user_id + " AND book_id = " + book_id;
			}
			pStatement = (PreparedStatement) conn.prepareStatement(sql2);
			pStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
	}

	public static boolean increaseCountInCart(int book_id) {
		String sql = "SELECT quantity FROM cart_details WHERE user_id = "
				+ AuthDAO.user_id + " AND book_id = " + book_id;
		String sql3 = "SELECT quantity FROM books WHERE book_id = " + book_id;
		ResultSet result;
		int quantity = 0, maxqty = 0;
		String sql2 = "";
		PreparedStatement pStatement = null;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			if (result.next()) {
				quantity = result.getInt(1);
				quantity = quantity + 1;
			}
			pStatement = (PreparedStatement) conn.prepareStatement(sql3);
			result = pStatement.executeQuery();
			if (result.next())
				maxqty = result.getInt(1);
			if (quantity <= maxqty) {
				sql2 = "UPDATE cart_details SET quantity = " + quantity
						+ " WHERE  user_id = " + AuthDAO.user_id
						+ " AND book_id = " + book_id;
			} else {
				return false;
			}
			pStatement = (PreparedStatement) conn.prepareStatement(sql2);
			pStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
	}

	public static double getBookShippingCost(int book_id) {
		String sql = "select shipping_cost from books where book_id = "
				+ book_id;

		ResultSet result = null;
		PreparedStatement ps;
		double shipping_cost = 0.0;

		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

			if (result.next()) {
				shipping_cost = result.getDouble("shipping_cost");
				return shipping_cost;
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

	public static boolean deleteCart(int cart_id) {
		String sql = "delete from cart_details where cart_id = " + cart_id;

		PreparedStatement pStatement = null;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			int returnValue = pStatement.executeUpdate();
			if (returnValue == 1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
		return false;
	}

	public List<Integer> getUserIdbyBookId(int bookId) {

		String sql = "select user_id from cart_details where book_id = "
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