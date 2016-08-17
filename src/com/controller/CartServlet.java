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

import com.model.Books;
import com.model.Cart;
import com.model.CartDao;
import com.model.User;
import com.model.WishlistDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		//System.out.println("Book_id : " + book_id);
		HttpSession session;
		session = request.getSession();
		session.removeAttribute("msgAddToCart");
		int user_id = (int) session.getAttribute("userId");
		String wishList = (String) request.getParameter("wishList");
		if(wishList == null) 
			wishList = "";
		CartDao cartObj = new CartDao();
		//System.out.println("Cart total : " + CartDao.getCartTotal(user_id));
		if(CartDao.addToCart(user_id, book_id, 1))
		{
			session.setAttribute("msgAddToCart", "Added to cart");
			//System.out.println("Cart total : " + CartDao.getCartTotal(user_id));
			if(wishList.equalsIgnoreCase("wishList")) {
				String url = "/WishList.jsp";
				result = CartDao.getCart();
				WishlistDAO wishlistDao = new WishlistDAO();
				wishlistDao.deleteItemFromWishList(book_id);
				session = request.getSession();
				session.setAttribute("cartList", result);
				pageNavigation(request, response, url);
				return;
			}
			result = CartDao.getCart();
			session = request.getSession();
			session.setAttribute("cartList", result);
		}
		else
		{
			session.setAttribute("msgAddToCart", "You have added the maximum number of book available");
			if(wishList.equalsIgnoreCase("wishList")) {
				String url = "/WishList.jsp";
				result = CartDao.getCart();
				session = request.getSession();
				session.setAttribute("cartList", result);
				pageNavigation(request, response, url);
				return;
			}
		}
		String url = "/SearchResult.jsp";
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
