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
 * Servlet implementation class WishListServlet
 */
@WebServlet("/WishListServlet")
public class WishListServlet extends HttpServlet {
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
		List<WishList> result = null;
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		//System.out.println("Book_id : " + book_id);
		HttpSession session;
		session = request.getSession();
		session.removeAttribute("msgAddToWishList");
		int user_id = (int) session.getAttribute("userId");
		WishlistDAO wishlistDao = new WishlistDAO();
		
		int insertWishList = wishlistDao.addToWishList(user_id, book_id);
		if(insertWishList == 1)
		{
			session.setAttribute("msgAddToWishList", "Added to Wish List");
			System.out.println("insertWishList == 1, before setting wishList in session ");
			result = wishlistDao.getWishList();
			if(result.isEmpty())
				System.out.println("getWishList result empty");
			session = request.getSession();
			session.setAttribute("wishList", result);
		}
		else if(insertWishList == 0)
		{
			session.setAttribute("msgAddToWishList", "Book already in your Wish List");
		}
		else {
			session.setAttribute("msgAddToWishList", "Add to Wish List failed");
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
