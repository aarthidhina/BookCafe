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

import com.model.Cart;
import com.model.CartDao;

/**
 * Servlet implementation class CartUpdateServlet
 */
@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Cart> result = null;
		// System.out.println("Book ID : " + request.getParameter("book_id"));
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		// System.out.println("Book_id : " + book_id);
		HttpSession session;
		session = request.getSession();
		session.removeAttribute("message");
		int user_id = (int) session.getAttribute("userId");
		CartDao cartObj = new CartDao();
		// System.out.println("Cart total : " + CartDao.getCartTotal(user_id));
		
		if (request.getParameter("remove").equalsIgnoreCase("delete")) {
			if (CartDao.deleteItemFromCart(book_id)) {
				request.setAttribute("message", "Deleted from cart");
				// System.out.println("Cart total : " +
				// CartDao.getCartTotal(user_id));
				result = CartDao.getCart();
				session = request.getSession();
				session.setAttribute("cartList", result);
			} else {
				request.setAttribute("message", "Failed to delete from cart");
			}
		}
		else if (request.getParameter("down").equalsIgnoreCase("countdown")) {
			//System.out.println("Reduce count");
			boolean updateCart = CartDao.reduceCountInCart(book_id);
			result = CartDao.getCart();
			session = request.getSession();
			if(updateCart == true) {
				session.setAttribute("message" , "Updated cart");
			}
			session.setAttribute("cartList", result);
		}
		else if (request.getParameter("up").equalsIgnoreCase("countup")) {
			//System.out.println("Increase count");
			boolean updateCart = CartDao.increaseCountInCart(book_id);
			result = CartDao.getCart();
			session = request.getSession();
			if(updateCart == false) {
				session.setAttribute("message", "Quantity ordered exceeded the quantity available");
			}
			else {
				
				session.setAttribute("message" , "Updated cart");
			}
			session.setAttribute("cartList", result);
		}
		String url = "/Shoppingcart.jsp";
		pageNavigation(request, response, url);
	}

	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}
}
