package com.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.model.Review;
import com.model.ReviewView;
import com.model.User;

/**
 * Servlet implementation class BookReviewViewServlet
 */
@WebServlet("/BookReviewViewServlet")
public class BookReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookReviewViewServlet() {
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

		ReviewView search = new ReviewView();
		List<Integer> result_order_no = null;
	
		ArrayList<Integer> result_book_no = new ArrayList<Integer>();
		result_book_no= new ArrayList<Integer>(new LinkedHashSet<Integer>(result_book_no));
		
		//List<Integer> result_book_no = null;
		
		String url = "/ReviewBookView.jsp";
				
		System.out.print("GETBookreviewViewservlet");
		User user = (User) session.getAttribute("user");
		//int seller_id=(int)session.getAttribute("seller_id_view");
		int book_id=Integer.parseInt(request.getParameter("book_id_view"));
		System.out.println("Seller Id from Add to cart jsp"+book_id);
		System.out.print(user.getUser_id());
		
		result_order_no = search.findbookReviewid(book_id);
		
		System.out.println("Result"+result_order_no);
		int[] array = new int[result_order_no.size()];
		for(int i = 0; i < result_order_no.size(); i++) {
			array[i] = result_order_no.get(i);
		}
		
	//	System.out.print(array);
	
	  
	 // session.setAttribute("bookresult", result_book_no);
		List<Review> result = null;
	// result= search.getBOOKSCart(result_book_no);
		result=search.getbookReviewDetails(result_order_no);
	 Review book = new Review();
	 for (int i = 0; i < result.size(); i++)
	 {
		 book=result.get(i);
		 System.out.println("BookReviewID"+book.getseller_review_id().toString());
		 System.out.println("Bookid"+book.get_book_id().toString());
		 System.out.println("Buyerid"+book.getBuyer_user_id().toString());
		 System.out.println("Reviewtext"+book.getReview_text().toString());
		 System.out.println("Rating"+book.getRanking().toString());
		 
	 }
	 // System.out.print(result_book_no);
	  session.setAttribute("resultList", result);
		  
		url = "/ReviewBookView.jsp";
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
	}
	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

}
