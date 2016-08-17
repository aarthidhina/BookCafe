package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthDAO;
import com.model.User;

/**
 * Servlet implementation class ContactBuyerServlet
 */
@WebServlet("/ContactSellerServlet")
public class ContactSellerServlet extends HttpServlet {
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

		String username = request.getParameter("username");

		String url = "/SellerContactInfo.jsp";
		int userId = 0;

		HttpSession session;
		session = request.getSession();
		AuthDAO authDao = new AuthDAO();

		ArrayList<User> sellerList = new ArrayList<User>();
		userId = authDao.getUserId(username);

//		if (username.isEmpty()) {
//			userId = -1;
//		} 
			sellerList = authDao.getAllSellers(userId);
			session.setAttribute("sellerList", sellerList);
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
