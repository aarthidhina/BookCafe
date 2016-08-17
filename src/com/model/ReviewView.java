package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewView {
	public static DatabaseConnection dc = null;
	private static Connection conn = null;

	public ReviewView() {
		dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	
	public static List<Integer> findSellerReviewid(int sellerID) {

		String sql = "select seller_review_id from  seller_review where seller_id='"+sellerID+"'";
				ResultSet result;
				ArrayList<Integer> review_no = new ArrayList<Integer>();

				System.out.println("In findSellerReviewid"+sql);

				try {

					PreparedStatement ps = conn.prepareStatement(sql);
					result = ps.executeQuery();

		         for(int i=0;result.next();i++){
		        	 int newnumber=result.getInt(1);
		        	 review_no.add(newnumber);
		        	          }
		        return review_no;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return review_no ;
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
					//System.out.println("Stack trace");
					e.printStackTrace();
					return review_no;
				}
				
			}


	public static List<Review> getSellerReviewDetails(List<Integer> seller_review_id){
		ArrayList<Review> seller_arraylist = new ArrayList<Review>();
		
		String sql = "select seller_review_id,seller_id,buyer_id,seller_review_text,seller_ranking from seller_review where seller_review_id in('";
		int[] array = new int[seller_review_id.size()];
		for(int i = 0; i < seller_review_id.size(); i++) {
			array[i] = seller_review_id.get(i);
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
				rev.setseller_review_id(result.getString(1));
				rev.setSeller_user_id(result.getString(2));
				rev.setBuyer_user_id(result.getString(3));
				rev.setReview_text(result.getString(4));
				rev.setRanking(result.getString(5));
				seller_arraylist.add(i, rev);
				
			}
			return seller_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}


	public static List<Integer> findbookReviewid(int bookid) {

		String sql = "select book_review_id from  book_review where book_id='"+bookid+"'";
				ResultSet result;
				ArrayList<Integer> review_no = new ArrayList<Integer>();

				System.out.println("In findSellerReviewid"+sql);

				try {

					PreparedStatement ps = conn.prepareStatement(sql);
					result = ps.executeQuery();

		         for(int i=0;result.next();i++){
		        	 int newnumber=result.getInt(1);
		        	 review_no.add(newnumber);
		        	          }
		        return review_no;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return review_no ;
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
					//System.out.println("Stack trace");
					e.printStackTrace();
					return review_no;
				}
				
			}


	public static List<Review> getbookReviewDetails(List<Integer> seller_review_id){
		ArrayList<Review> seller_arraylist = new ArrayList<Review>();
		
		String sql = "select book_review_id,book_id,buyer_id,book_review_text,book_ranking from book_review where book_review_id in('";
		int[] array = new int[seller_review_id.size()];
		for(int i = 0; i < seller_review_id.size(); i++) {
			array[i] = seller_review_id.get(i);
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
				rev.setseller_review_id(result.getString(1));
				rev.set_book_id(result.getString(2));
				rev.setBuyer_user_id(result.getString(3));
				rev.setReview_text(result.getString(4));
				rev.setRanking(result.getString(5));
				seller_arraylist.add(i, rev);
				
			}
			return seller_arraylist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found : " + e.getMessage());
			return null;
		}
	}


}
