package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public OrderDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}

	public static int insertIntoOrder(int buyer_id, String order_status,
			double tax, double total_price, int total_quantity) {

		String sql = "INSERT INTO orders (`buyer_id`, `order_status`, `tax`, `total_price`, "
				+ "`total_quantity`, `order_time`) VALUES ("
				+ buyer_id
				+ ",\""
				+ order_status
				+ "\","
				+ tax
				+ ","
				+ total_price
				+ ","
				+ total_quantity + ", " + "CURTIME() )";
		// System.out.println("SQL : " + sql);
		String sql2 = "select order_no from orders order by order_no desc";

		PreparedStatement pStatement = null;
		ResultSet result;
		int resultValue;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			resultValue = pStatement.executeUpdate();
			if (resultValue == 1) {
				// System.out.println("Inside enterNewUser:  SQL2 = " + sql2);
				pStatement = conn.prepareStatement(sql2);
				result = pStatement.executeQuery();
				while (result.next()) {
					return result.getInt(1);
				}
			}
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	public static boolean insertIntoOrderShippingDetails(int order_no,
			String full_name, String addr1, String addr2, String city,
			String state, String zipcode, String phone) {

		// System.out.println("Inside insertIntoOrderShippngDetails...");
		String sql = "INSERT INTO order_shipping_details (`order_no`, `full_name`, `shipping_addr1`, "
				+ "`shipping_addr2`, `city`, `state`, `zipcode`, `phone`) VALUES ("
				+ order_no
				+ ",\""
				+ full_name
				+ "\",\""
				+ addr1
				+ "\",\""
				+ addr2
				+ "\",\""
				+ city
				+ "\",\""
				+ state
				+ "\",\""
				+ zipcode
				+ "\",\"" + phone + "\" )";

		PreparedStatement pStatement = null;
		int returnValue;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			returnValue = pStatement.executeUpdate();
			if (returnValue == 1) {
				return true;
			}
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;

	}

	public static boolean insertIntoOrderDetails(int order_no, int seller_id,
			int book_id, int quantity, double price, double shipping_cost,
			String orderStatus) {
		// System.out.println("Inside insertIntoOrderDetails...");

		String sql = "insert into order_details ( `order_no`, `seller_id`, `book_id`, `quantity`, "
				+ "`price`, `shipping_cost`, `status`) values ("
				+ order_no
				+ ","
				+ seller_id
				+ ","
				+ book_id
				+ ","
				+ quantity
				+ ","
				+ price + "," + shipping_cost + ", \"" + orderStatus + "\")";
		// System.out.println("SQL : " + sql);
		PreparedStatement pStatement = null;
		int returnValue;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			returnValue = pStatement.executeUpdate();
			if (returnValue == 1) {
				return true;
			}
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;

	}

	public static boolean insertIntoBuyerPaymentInfo(int buyer_id,
			String card_no, String expiry_date, String security_code) {
		String sql = "INSERT INTO buyer_payment_info (`buyer_id`, `card_no`, `expiry_date`, `security_code`) VALUES ("
				+ buyer_id
				+ ",\""
				+ card_no
				+ "\",\""
				+ expiry_date
				+ "\",\""
				+ security_code + "\")";
		// System.out.println("SQL = " + sql);
		PreparedStatement pStatement = null;
		int returnValue;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			returnValue = pStatement.executeUpdate();
			if (returnValue == 1) {
				return true;
			}
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static int cancelOrder(int order_id) {
		PreparedStatement pStatement = null;
		String sql1 = "";
		// System.out.println("User ID : " + AuthDAO.user_id);
		AuthDAO authdao = new AuthDAO();
		String userType = AuthDAO.getUserPrivilege(authdao.user_id);
		// System.out.println("User Type : " + userType);
		if (userType.equalsIgnoreCase("buyer")
				|| userType.equalsIgnoreCase("admin")) {
			sql1 = "UPDATE orders, order_details SET orders.order_status='CANCELLED',order_details.status='CANCELLED' where orders.order_no="
					+ order_id + " AND order_details.order_no=" + order_id;
		} else {
			sql1 = "UPDATE order_details SET order_details.status='CANCELLED' where order_details.seller_id="
					+ AuthDAO.user_id
					+ " AND order_details.order_no="
					+ order_id;
		}
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			pStatement.executeUpdate();
			OrderDAO.updateOrderStatus(order_id);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int cancelPartialOrder(int order_id, int book_id, int qty) {
		PreparedStatement pStatement = null;
		String sql1 = "";
		// System.out.println("User ID : " + AuthDAO.user_id);
		AuthDAO authdao = new AuthDAO();
		String userType = AuthDAO.getUserPrivilege(authdao.user_id);
		// System.out.println("User Type : " + userType);
		AddBookDAO addbookdao = new AddBookDAO();
		int currBookQty = addbookdao.getBookQuantity(book_id);
		currBookQty = currBookQty + qty;
		addbookdao.updateBookQuantity(book_id, currBookQty);
		sql1 = "UPDATE order_details SET order_details.status='CANCELLED' where order_details.book_id="
				+ book_id + " AND order_details.order_no=" + order_id;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			return pStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int getBuyerId(int order_id) {
		String sql = "select buyer_id from orders where order_no = " + order_id;
		PreparedStatement pStatement = null;
		ResultSet result = null;

		try {
			pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			while (result.next()) {
				return result.getInt("buyer_id");
			}

			pStatement.close();

		} catch (SQLException e) {
			return -1;
		}
		return -1;
	}

	public static int updateCompleteOrderStatus(int order_id, String status) {
		PreparedStatement pStatement = null;
		String sql1 = "";
		// System.out.println("User ID : " + AuthDAO.user_id);
		AuthDAO authdao = new AuthDAO();
		String userType = AuthDAO.getUserPrivilege(authdao.user_id);
		// System.out.println("User Type : " + userType);
		if (userType.equalsIgnoreCase("seller")) {
			sql1 = "UPDATE order_details SET order_details.status='" + status
					+ "' where order_details.seller_id=" + authdao.user_id
					+ " AND order_details.order_no=" + order_id;
		} else if (userType.equalsIgnoreCase("admin")) {
			sql1 = "UPDATE order_details SET order_details.status='" + status
					+ "' where order_details.order_no=" + order_id;
		}
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			pStatement.executeUpdate();
			OrderDAO.updateOrderStatus(order_id);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int updateProductOrderStatus(int order_id, String status,
			int book_id) {
		PreparedStatement pStatement = null;
		String sql1 = "";
		// System.out.println("User ID : " + AuthDAO.user_id);
		AuthDAO authdao = new AuthDAO();
		// String userType = AuthDAO.getUserPrivilege(authdao.user_id);
		// System.out.println("User Type : " + userType);
		sql1 = "UPDATE order_details SET order_details.status='" + status
				+ "' where order_details.order_no=" + order_id
				+ " AND order_details.book_id=" + book_id;
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			pStatement.executeUpdate();
			OrderDAO.updateOrderStatus(order_id);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static List<ViewOrder> viewOrder(int user_id) {
		ArrayList<ViewOrder> orders_arraylist = new ArrayList<ViewOrder>();
		PreparedStatement pStatement = null;
		ResultSet result;
		String privilege = "";
		String sql1 = "select user_privilege from user where user_id="
				+ user_id;
		// System.out.println("User ID : "+user_id);
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			result = pStatement.executeQuery();
			if (result.next()) {
				switch (result.getString("user_privilege")) {
				case "buyer":
					privilege = " AND orders.buyer_id=" + user_id;
					break;
				case "seller":
					privilege = " AND order_details.seller_id=" + user_id;
					break;
				case "admin":
					privilege = "";
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		String sql = "select orders.buyer_id, orders.order_status, order_details.status, order_details.book_id, user_profile.first_name, user_profile.middle_name, user_profile.last_name, order_details.seller_id, orders.order_no, order_status, total_price, orders.total_quantity, order_time, books.shipping_cost, books.title, books.author, order_details.quantity, books.price from orders, order_details, books, user_profile where order_details.seller_id=user_profile.user_id AND orders.order_no=order_details.order_no AND order_details.book_id=books.book_id"
				+ privilege + " ORDER BY orders.order_no DESC";
		// System.out.println("SQL : "+sql);
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			// System.out.println("I am here 5");
			// ViewOrder order = new ViewOrder();
			for (int i = 0; result.next(); i++) {
				ViewOrder order = new ViewOrder();
				order.setBuyer_id(result.getInt("buyer_id"));
				order.setSeller_id(result.getInt("seller_id"));
				order.setOrder_id(result.getInt("order_no"));
				order.setSellerName(result.getString("first_name") + " "
						+ result.getString("middle_name") + " "
						+ result.getString("last_name"));
				order.setOrder_status(result.getString("order_status"));
				order.setTotal_price(result.getFloat("total_price"));
				order.setOrder_time(result.getDate("order_time"));
				order.setBookTitle(result.getString("title"));
				order.setQuantity(result.getInt("quantity"));
				order.setProduct_status(result.getString("status"));
				order.setOrder_status(result.getString("order_status"));
				order.setPrice(result.getDouble("price"));
				order.setBook_id(result.getInt("book_id"));
				order.setShippingCost(result.getDouble("shipping_cost"));
				order.setTotal_quantity(result.getInt("total_quantity"));
				orders_arraylist.add(i, order);
				// System.out.println("I am here i : "+i);
			}
			// System.out.println("I am here 6");
			return orders_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public List<ViewOrder> getOrderDetails(int orderId) {
		String sql = "select * from orders natural join order_details "
				+ "natural join order_shipping_details where orders.order_no = "
				+ orderId;
		PreparedStatement pStatement = null;
		List<ViewOrder> orders_arraylist = new ArrayList<ViewOrder>();
		ResultSet result;

		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++) {
				ViewOrder order = new ViewOrder();
				order.setOrder_id(result.getInt("order_no"));				
				order.setTotal_price(result.getFloat("total_price"));
				order.setShipping_addr(result.getString("shipping_addr1"));
				order.setShipping_addr2(result.getString("shipping_addr2"));
				order.setCity(result.getString("city"));
				order.setState(result.getString("state"));
				order.setZipcode(result.getString("zipcode"));
				order.setPhone(result.getString("phone"));
				order.setBook_id(result.getInt("book_id"));
				order.setQuantity(result.getInt("quantity"));				
				orders_arraylist.add(i, order);
				// System.out.println("I am here i : "+i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("I am here 6");
		return orders_arraylist;
	}

	public static int updateOrderStatus(int order_id) {
		ArrayList<ViewOrder> orders_arraylist = new ArrayList<ViewOrder>();
		PreparedStatement pStatement = null;
		ResultSet result;
		String privilege = "";
		String sql1 = "select order_status, order_details.status from orders, order_details where orders.order_no="
				+ order_id + " AND orders.order_no=order_details.order_no";
		// System.out.println("SQL : "+sql);
		try {
			pStatement = (PreparedStatement) conn.prepareStatement(sql1);
			result = pStatement.executeQuery();
			// pStatement = (PreparedStatement) conn.prepareStatement(sql);
			// result = pStatement.executeQuery();
			// System.out.println("I am here 5");
			// ViewOrder order = new ViewOrder();
			boolean submitted = true;
			boolean cancelled = true;
			boolean processing = true;
			boolean completed = true;
			boolean shipped = true;
			String status = "";
			for (; result.next();) {
				if (!result.getString("status").equalsIgnoreCase("submitted")) {
					submitted = false;
				}
				if (!result.getString("status").equalsIgnoreCase("cancelled")) {
					cancelled = false;
				}
				if (!result.getString("status").equalsIgnoreCase("processing")) {
					processing = false;
				}
				if (!result.getString("status").equalsIgnoreCase("completed")) {
					completed = false;
				}
				if (!result.getString("status").equalsIgnoreCase("shipped")) {
					shipped = false;
				}
				if (submitted)
					status = "SUBMITTED";
				else if (cancelled)
					status = "CANCELLED";
				else if (completed)
					status = "COMPLETED";
				else if (shipped)
					status = "SHIPPED";
				else
					status = "PROCESSING";
			}
			// System.out.println("Status : "+status);
			String sql = "UPDATE orders SET order_status='" + status
					+ "' where order_no=" + order_id;
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			int rslt = pStatement.executeUpdate();
			return rslt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}

	}

	public static boolean deleteOrder(int order_no) {
		String sql1 = "delete from order_shipping_details where order_no = "
				+ order_no;
		String sql2 = "delete from order_details where order_no = " + order_no;
		String sql3 = "delete from orders where order_no = " + order_no;

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		try {
			pStatement1 = conn.prepareStatement(sql1);
			pStatement2 = conn.prepareStatement(sql2);
			pStatement3 = conn.prepareStatement(sql3);
			int returnValue1 = pStatement1.executeUpdate();
			int returnValue2 = pStatement2.executeUpdate();
			int returnValue3 = pStatement3.executeUpdate();
			if (returnValue1 == 1 && returnValue2 == 1 && returnValue3 == 1)
				return true;
		} catch (SQLException e) {
			System.out.println("Class Not Found : " + e.getMessage());
			return false;
		}
		return false;
	}
	
	public static List<Integer> getBestSeller() {
//		System.out.println("inside getBestSeller");
		String sql = "select book_id from order_details GROUP BY book_id ORDER BY SUM(quantity) DESC LIMIT 5";
//		System.out.println("Sql = " + sql);
		PreparedStatement pStatement = null;
		ResultSet result = null;
		List<Integer> bestSeller = new ArrayList<>();

		try {
			pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++)  {
				bestSeller.add(i, result.getInt("book_id"));
			}
			
//			for (int i = 0; i < bestSeller.size(); i++)  {
//				System.out.println(bestSeller.get(i));
//			}
			
			pStatement.close();

		} catch (SQLException e) {
			return null;
		}
		return bestSeller;
	}

}
