package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DeleteUser;
import com.model.EmailClient;
import com.model.AuthDAO;
import com.model.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
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
		String url = "/Login.jsp";
//		System.out.println("Delete user");
		HttpSession session;
		session = request.getSession();
		int user_id = (int) session.getAttribute("userId");
		boolean result = false;
		DeleteUser del = new DeleteUser();
		result = del.deleteuser(user_id);
		if (result == true) {
			session.invalidate();
		} else {
			session.invalidate();
		}
		request.setAttribute("msgFromServlet", "You account has been deleted");
		pageNavigation(request, response, url);
	}

	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/Welcome.jsp";
//		System.out.println("Delete user post");
		HttpSession session;
		DeleteUser del = new DeleteUser();
		boolean result = false;
		EmailClient ec = new EmailClient();
		session = request.getSession();
		AuthDAO auth = new AuthDAO();
		
		int user_id = Integer.parseInt(request
				.getParameter("user_idfordeletion"));
		
		String actiontoperform = request.getParameter("actiontotake");
		// result=del.approveuser(user_id);
//		System.out.print("user_id" + user_id);
//		System.out.print("actiontoperform" + actiontoperform);
		User user = auth.getUserById(user_id);
		
		if (actiontoperform.equals("approve")) {
//			System.out.print("Approved Seller" + user_id);
			result = del.approveuser(user_id);
			ec.sendSellerApproveRegistrationEmail(user, auth.getUserName(user_id));
			pageNavigation(request, response, url);
		} else {
			ec.sendSellerDeclineRegistrationEmail(user);
			pageNavigation(request, response, url);
		}

	}

}
