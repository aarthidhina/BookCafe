package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthDAO;
import com.model.DeleteUser;
import com.model.EmailClient;

/**
 * Servlet implementation class ActivateAccountServlet
 */
@WebServlet("/ActivateAccountServlet")
public class ActivateAccountServlet extends HttpServlet {
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
		String url = "/Login.jsp";
		String activateAccount = request.getParameter("activateAccount");
		DeleteUser activate = new DeleteUser();
		AuthDAO auth = new AuthDAO();

		if (activateAccount.equalsIgnoreCase("no")) {
			HttpSession session = request.getSession();
			session.invalidate();

			request.setAttribute("msgFromServlet", "");
			pageNavigation(request, response, url);
			return;
		}
		else if(activateAccount.equalsIgnoreCase("yes")) {
			activate.activateAccount(AuthDAO.user_id);
			EmailClient ec = new EmailClient();
			ec.sendAccountActivatedEmail(auth.getUserById(AuthDAO.user_id));
			HttpSession session = request.getSession();
			session.invalidate();
			
			request.setAttribute("msgFromServlet", "Your account is activated. Please log in.");
			pageNavigation(request, response, url);
			return;
		}
	}
	
	private void pageNavigation(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(url);
		rDispatch.forward(request,response);
	}

}
