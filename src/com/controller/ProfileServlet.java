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
import com.model.EditBookDAO;
import com.model.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		int userId = user.getUser_id();
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		String msgFromServlet = "";
		String url;

		boolean result = false;
		
		
		if (firstName == null) { firstName = ""; }
		if (middleName == null) { middleName = ""; }
		if (lastName == null) { lastName = ""; }
		if (email == null) { email = ""; }
		if (address1 == null) { address1 = ""; }
		if (address2 == null) { address2 = ""; }
		if (city == null) { city = ""; }
		if (state == null) { state = ""; }
		if (zipcode == null) { zipcode = ""; }
		if (phone == null) { phone = ""; }
		if (password == null) { password = ""; }
		if (confirmPassword == null) { password = ""; }
		
		
		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || address1.isEmpty() ||
				city.isEmpty() || state.isEmpty() || zipcode.isEmpty() || phone.isEmpty()) {

			msgFromServlet = "First Name, Last Name, Email, Address 1, City, State, Zip, and Phone are required!";

			request.setAttribute("msgFromServlet", msgFromServlet);
			url = "/EditProfile.jsp";
			pageNavigation(request, response, url);
		}
		else if (!password.isEmpty() && (!password.equals(confirmPassword)))
		{
			msgFromServlet = "Passwords do not match!  Please enter a matching password.";
			
			request.setAttribute("msgFromServlet", msgFromServlet);
			url = "/EditProfile.jsp";
			pageNavigation(request, response, url);
		}
		else{
			
			AuthDAO auth = new AuthDAO();
			
			if (!password.isEmpty() && (password.equals(confirmPassword)))
			{
				result = auth.updatePassword(userId, password);
				
				if (result == true)
				{
					msgFromServlet = "Password updated successfully!  ";
				}
				else
				{
					msgFromServlet = "Error: Password not updated.  ";
				}
			}
			
			result = auth.updateProfile(userId, firstName, middleName, lastName, email, address1,
					address2, city, state, zipcode, phone);
			
			if (result == true)
			{
				msgFromServlet = msgFromServlet + "Profile updated successfully!";
			}
			else
			{
				msgFromServlet = msgFromServlet + "Error: Profile not updated.";
			}
	
			user = auth.getUserById(userId);
			
			session.setAttribute("user", user);
			
			request.setAttribute("msgFromServlet", msgFromServlet);
			url = "/EditProfile.jsp";
			
			try {
				AuthDAO.DB_Close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			pageNavigation(request, response, url);
		}
	}
	
	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

}
