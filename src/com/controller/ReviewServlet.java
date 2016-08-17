package com.controller;

import java.io.IOException;
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
import com.model.OrderDAO;
import com.model.SearchDAO;
import com.model.ViewOrder;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub

			
			String url = "/ReviewSeller.jsp";
			if(request.getParameter("ReviewSeller")!=null)
			{
				int seller_id = Integer.parseInt(request.getParameter("seller_id"));
				int book_id = Integer.parseInt(request.getParameter("book_id"));
				request.setAttribute("seller_id", seller_id);
				request.setAttribute("book_id", book_id);
				pageNavigation(request, response, url);
				return;
			}
			if(request.getParameter("ReviewBook")!=null)
			{
				url = "/ReviewBook.jsp";
				int seller_id = Integer.parseInt(request.getParameter("seller_id"));
				int book_id = Integer.parseInt(request.getParameter("book_id"));
				request.setAttribute("seller_id", seller_id);
				request.setAttribute("book_id", book_id);
				pageNavigation(request, response, url);
				return;
			}
			if(request.getParameter("SellerReview")!=null)
			{
				url = "/ViewOrder.jsp";
				int seller_id = Integer.parseInt(request.getParameter("seller_id"));
				int rating = Integer.parseInt(request.getParameter("rating"));
				String review_text = request.getParameter("review_text");
				System.out.println("seller_id : "+seller_id);
				System.out.println("Rating : "+rating);
				System.out.println("review_text : "+review_text);
				if(!AuthDAO.updateSellerReview(seller_id, rating, review_text))
				{
					request.setAttribute("error", "Failed to add review");
					url = "/ReviewSeller.jsp";
				}else{
				List<ViewOrder> orderList = null;
				OrderDAO order = new OrderDAO();
				//System.out.println("I am here 1");
				orderList = order.viewOrder(AuthDAO.user_id);
				request.setAttribute("orderList", orderList);
				request.setAttribute("message", "Review Added");
				}
				pageNavigation(request, response, url);
				return;
			}
			if(request.getParameter("BookReview")!=null)
			{
				url = "/ViewOrder.jsp";
				int book_id = Integer.parseInt(request.getParameter("book_id"));
				int rating = Integer.parseInt(request.getParameter("rating"));
				String review_text = request.getParameter("review_text");
//				System.out.println("seller_id : "+book_id);
//				System.out.println("Rating : "+rating);
//				System.out.println("review_text : "+review_text);
				if(!AuthDAO.updateBookReview(book_id, rating, review_text))
				{
					request.setAttribute("error", "Failed to add review");
					url = "/ReviewBook.jsp";
				}else{
				List<ViewOrder> orderList = null;
				OrderDAO order = new OrderDAO();
				//System.out.println("I am here 1");
				orderList = order.viewOrder(AuthDAO.user_id);
				request.setAttribute("orderList", orderList);
				request.setAttribute("message", "Review Added");
				}
				pageNavigation(request, response, url);
				return;
			}
				return;
		}

		private void pageNavigation(HttpServletRequest request,
				HttpServletResponse response, String url) throws ServletException,
				IOException {
			RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
					url);
			rDispatch.forward(request, response);
		}

}
