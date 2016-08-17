package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthDAO;
import com.model.BookReviewDAO;
import com.model.Books;
import com.model.Review;
import com.model.User;

/**
 * Servlet implementation class SellerReviewServlet
 */
@WebServlet("/SellerReviewServlet")
public class SellerReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		session = request.getSession();

		BookReviewDAO search = new BookReviewDAO();
		List<Integer> result_order_no = null;
		List<Integer> result_book_no_temp = null;
		ArrayList<Integer> result_book_no = new ArrayList<Integer>();
		result_book_no= new ArrayList<Integer>(new LinkedHashSet<Integer>(result_book_no));
		
		//List<Integer> result_book_no = null;
		
		String url = "/ApproveRegistration.jsp";
				
		System.out.print("GETBookreviewservlet");
		User user = (User) session.getAttribute("user");
		
		System.out.print(user.getUser_id());
		
		result_order_no = search.findOrderno(user.getUser_id());
		
		System.out.println("Result"+result_order_no);
		int[] array = new int[result_order_no.size()];
		for(int i = 0; i < result_order_no.size(); i++) {
			array[i] = result_order_no.get(i);
		}
		
		System.out.print(array);
		for(int i=0;i<array.length;i++){
			
			result_book_no_temp=search.findSellerid(array[i]);
			 System.out.print("findSellerid "+result_book_no_temp);
			 Iterator<Integer> myListIterator = result_book_no_temp.iterator();
			 while (myListIterator.hasNext()) {
				    Integer coord = myListIterator.next();  
				    System.out.println("Sellerid "+coord);
				    result_book_no.add(coord) ;
			 }
	
				
		}
		HashSet hs = new HashSet();
		hs.addAll(result_book_no);
		result_book_no.clear();
		result_book_no.addAll(hs);
	  
	 // session.setAttribute("bookresult", result_book_no);
		List<Review> result = null;
	 result= search.getSellerCart(result_book_no);
	 Review rev = new Review();
	 for (int i = 0; i < result.size(); i++)
	 {
		 rev=result.get(i);
		 System.out.println("Seller id"+rev.getSeller_user_id().toString());
		 System.out.println("SellerFirstName"+rev.getSeller_First_name().toString());
		 System.out.println("SellerLastName"+rev.getSeller_Last_name().toString());
		 
	 }
	 // System.out.print(result_book_no);
	  session.setAttribute("resultList", result);
		  
		url = "/ReviewSellerNew.jsp";
		pageNavigation(request, response, url);
		try {
			AuthDAO.DB_Close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/Welcome.jsp";
		System.out.println("Seller Review user post");
		HttpSession session;
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		int user_id=user.getUser_id();
		int rating=Integer.parseInt(request.getParameter("rating"));
		String review_text=request.getParameter("review_text");
		int Seller_id=Integer.parseInt(request.getParameter("Seller_id"));
		
		boolean result=false;
		//DeleteUser del=new DeleteUser();
		//result=del.approveuser(user_id);
		System.out.print("user_id"+user_id);
		System.out.print("rating"+rating);
		System.out.print("review_text"+review_text);
		System.out.print("Book_id"+Seller_id);
		BookReviewDAO bookr=new BookReviewDAO();
		bookr.updateSellerReview(user_id,Seller_id,rating,review_text);
		if(result==true)
		{
			//System.out.print("Approved Seller"+user_id);
			//result=del.approveuser(user_id);
			pageNavigation(request, response, url);
		}
		else{pageNavigation(request, response, url);}
	}

	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

}
