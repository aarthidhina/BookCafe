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
import com.model.WishList;
import com.model.WishlistDAO;

/**
 * Servlet implementation class WishlistUpdateServlet
 */
@WebServlet("/WishlistUpdateServlet")
public class WishlistUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<WishList> result = null;
		// System.out.println("Book ID : " + request.getParameter("book_id"));
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		// System.out.println("Book_id : " + book_id);
		HttpSession session;
		session = request.getSession();
		session.removeAttribute("wishListmessage");
		int user_id = (int) session.getAttribute("userId");
		WishlistDAO wishlistDao = new WishlistDAO();
		// System.out.println("Cart total : " + CartDao.getCartTotal(user_id));
		
		if (request.getParameter("remove").equalsIgnoreCase("delete")) {
			if (WishlistDAO.deleteItemFromWishList(book_id)) {
				request.setAttribute("wishListmessage", "Updated Wish List");
				// System.out.println("Cart total : " +
				// CartDao.getCartTotal(user_id));
				result = WishlistDAO.getWishList();
				session = request.getSession();
				session.setAttribute("wishList", result);
			} else {
				request.setAttribute("wishListmessage", "Failed to delete from Wish List");
			}
		}		
		String url = "/WishList.jsp";
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
