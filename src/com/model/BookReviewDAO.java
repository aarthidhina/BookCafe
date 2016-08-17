package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookReviewDAO {
	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public BookReviewDAO() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	public static List<Integer> findOrderno(int userName) {

		String sql = "SELECT order_no FROM orders WHERE buyer_id = '" + userName
				+ "'";
		ResultSet result;
		ArrayList<Integer> book_orderno = new ArrayList<Integer>();

		System.out.println("In findOrderno"+sql);

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

         for(int i=0;result.next();i++){
        	 int newnumber=result.getInt(1);
        	 book_orderno.add(newnumber);
        	          }
        return book_orderno;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return book_orderno ;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			//System.out.println("Stack trace");
			e.printStackTrace();
			return book_orderno;
		}
		
	}

	@SuppressWarnings("resource")
	
	public static List<Books> getBOOKSCart(ArrayList<Integer> result_book_no) {
		ArrayList<Books> book_arraylist = new ArrayList<Books>();
		/*
		 * String sql =
		 * "select book_id, seller_id, quantity from cart_details where user_id = "
		 * + AuthDAO.user_id;
		 */
		String sql = "select book_id,title,author from books where book_id in('";
		int[] array = new int[result_book_no.size()];
		for(int i = 0; i < result_book_no.size(); i++) {
			array[i] = result_book_no.get(i);
		}
		if(array.length==0)
		{
			sql+="'";
		}
		else
		if(array.length==1)
		{
		sql+=array[0]+"'";
		}
		else{
        for(int i=0;i<array.length;i++)
        {
        	if(i==0)
        {
        	sql+=array[0]+"'";
        }
        else{
        	sql+=",'"+array[i]+"'";
        }
        }
		}
		sql+=")";
		System.out.println("SQL : " + sql);
		ResultSet result;
		PreparedStatement pStatement = null;
		try {
			// System.out.println("I am here 1");
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++) {
				Books app = new Books();
				app.setBook_id(result.getString(1));
				app.setTitle(result.getString(2));
				app.setAuthor(result.getString(3));
				
				// System.out.println("Count : " + total_quantity);
				book_arraylist.add(i, app);
				// System.out.println("Size : " + cart_arraylist.size());
			}
			return book_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}
//last method
	public static List<Integer> findBookno(int book_id) {

String sql = "select distinct book_id from order_details where order_no='"+book_id+"'";
		ResultSet result;
		ArrayList<Integer> book_no = new ArrayList<Integer>();

		System.out.println("In findBookno"+sql);

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

         for(int i=0;result.next();i++){
        	 int newnumber=result.getInt(1);
        	 book_no.add(newnumber);
        	          }
        return book_no;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return book_no ;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			//System.out.println("Stack trace");
			e.printStackTrace();
			return book_no;
		}
		
	}


	public static boolean updateBookReview(int user_id,int Book_id,int rating,String review_text) {

		String insertSql = "insert into book_review(buyer_id,book_review_text,book_ranking,book_id) values('"
				+ user_id + "','"+review_text+"','"+rating+"','"+Book_id+"')";
		
		System.out.println("SQL in deleteuser = " + insertSql);
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


//For Seller Review
	
	public static List<Review> getSellerCart(ArrayList<Integer> result_book_no){
		ArrayList<Review> seller_arraylist = new ArrayList<Review>();
		
		String sql = "select user_id,first_name,last_name from user_profile where user_id in('";
		int[] array = new int[result_book_no.size()];
		for(int i = 0; i < result_book_no.size(); i++) {
			array[i] = result_book_no.get(i);
		}
		if(array.length==0)
		{
			sql+="'";
		}
		else
		if(array.length==1)
		{
		sql+=array[0]+"'";
		}
		else{
        for(int i=0;i<array.length;i++)
        {
        	if(i==0)
        {
        	sql+=array[0]+"'";
        }
        else{
        	sql+=",'"+array[i]+"'";
        }
        }
		}
		sql+=")";
		System.out.println("SQL : " + sql);
		ResultSet result;
		PreparedStatement pStatement = null;
		try {
			// System.out.println("I am here 1");
			pStatement = (PreparedStatement) conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			for (int i = 0; result.next(); i++) {
				Review rev = new Review();
				
				rev.setSeller_user_id(result.getString(1));
				rev.setSeller_First_name(result.getString(2));
				rev.setSeller_last_name(result.getString(3));
				
				seller_arraylist.add(i, rev);
				
			}
			return seller_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}

	public static List<Integer> findSellerid(int order_no) {

		String sql = "select distinct seller_id from order_details where order_no='"+order_no+"'";
				ResultSet result;
				ArrayList<Integer> book_no = new ArrayList<Integer>();

				System.out.println("In findSellerid"+sql);

				try {

					PreparedStatement ps = conn.prepareStatement(sql);
					result = ps.executeQuery();

		         for(int i=0;result.next();i++){
		        	 int newnumber=result.getInt(1);
		        	 book_no.add(newnumber);
		        	          }
		        return book_no;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return book_no ;
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
					//System.out.println("Stack trace");
					e.printStackTrace();
					return book_no;
				}
				
			}


	public static boolean updateSellerReview(int user_id, int seller_id, int rating, String review_text) {
		String insertSql = "insert into seller_review(seller_id,seller_review_text,seller_ranking,buyer_id) values("
				+ seller_id + ",'"+review_text+"',"+rating+","+user_id+")";
		
		System.out.println("SQL in updateSellerReview = " + insertSql);
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

