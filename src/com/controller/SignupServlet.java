package com.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AuthDAO;
import com.model.EmailClient;
import com.model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pattern pattern;
		Matcher matcher;
		
		final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		
		String userName = request.getParameter("username");
		String firstName = request.getParameter("firstname");
		String middleName = request.getParameter("middlename");
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String phone = request.getParameter("phone");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		String userPrivilege = request.getParameter("userPrivilege");
		
		String errorUserName = "";		
		String errorFirstName = "";
		String errorLastName = "";
		String errorPassword = "";
		String errorConfirmPassword = "";
		String errorAddress1 = "";
		String errorEmail = "";
		String errorCity = "";
		String errorState = "";
		String errorZipCode = "";
		String errorPhone = "";
		String msgRegisterStatus = "";
		String msgAvailableUserName = "";	
		
		EmailClient ec = new EmailClient();
		
		String url = "/Signup.jsp";		
		int userId = -1;
				
		AuthDAO auth = new AuthDAO();
		
		if(request.getParameter("check") != null)	{

			if(userName.isEmpty())
				errorUserName = "Username not filled in";
	
			else if(auth.isUserNameAvailable(userName))	
					errorUserName = "Username Not Available";
			else
				errorUserName = "Username Available";
			
			request.setAttribute("msgCheckUserName", errorUserName);
			request.setAttribute("msgAvailableCheckUserName", msgAvailableUserName);
			pageNavigation(request, response, url);
			return;			
		}
		
		
		if(request.getParameter("signup") != null)	{

	// This if statement is not specified in the Lab document		
			if(userName.isEmpty() && firstName.isEmpty() && 
					lastName.isEmpty() && password.isEmpty() && confirmPassword.isEmpty())	{

				request.setAttribute("registerStatus", "Please fill the form");
				
				pageNavigation(request, response, url);
				return;
			}
			
				else if(userName.isEmpty() || firstName.isEmpty() || 
							lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
		
						if(userName.isEmpty())
							errorUserName = "Username not filled in";

						if(firstName.isEmpty())
							errorFirstName = "FirstName not filled in";
			
						if(lastName.isEmpty())
							errorLastName = "Last Name not filled in";

						if(password.isEmpty())
							errorPassword = "password not filled in";		
						
						if(confirmPassword.isEmpty())
							errorConfirmPassword = "Confirm password not filled in";

						if(address1.isEmpty())
							errorAddress1 = "Address 1 not filled in";
						
						if(email.isEmpty())
							errorEmail = "Email not filled in";					
						
						if(city.isEmpty())
							errorCity = "City not filled in";
						
						if(state.isEmpty())
							errorState = "State not filled in";
						
						if(zipcode.isEmpty())
							errorZipCode = "Zip Code not filled in";
						
						if(phone.isEmpty())
							errorPhone = "Phone not filled in";
						
						request.setAttribute("message", msgRegisterStatus);
						request.setAttribute("errorUserName", errorUserName);
						request.setAttribute("errorFirstName", errorFirstName);
						request.setAttribute("errorLastName", errorLastName);
						request.setAttribute("errorPassword", errorPassword);
						request.setAttribute("errorConfirmPassword", errorConfirmPassword);
						request.setAttribute("errorAddress1", errorAddress1);
						request.setAttribute("errorCity", errorCity);
						request.setAttribute("errorState", errorState);
						request.setAttribute("errorZipCode", errorZipCode);
						request.setAttribute("errorPhone", errorPhone);
						request.setAttribute("errorEmail", errorEmail);
								
						pageNavigation(request, response, url);
						return;
		}
		else	{
			if(!password.equals(confirmPassword))	{
				errorPassword = "Password Does Not Match";
				request.setAttribute("errorPassword", errorPassword);				
				pageNavigation(request, response, url);
				return;
			}
			else if(auth.isUserNameAvailable(userName))	{
				errorUserName = "Username Not Available";
				request.setAttribute("errorUserName", errorUserName);
				pageNavigation(request, response, url);
				return;
			}
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			boolean result = matcher.matches();
			
			if(result == false) {
				errorEmail = "Email not valid";
				request.setAttribute("errorEmail", errorEmail);
				pageNavigation(request, response, url);
				return;
			}
			
			if(auth.checkEmailExists(email)) {
				errorEmail = "Email already exists";
				request.setAttribute("errorEmail", errorEmail);
				pageNavigation(request, response, url);
				return;
			}
			
			if(!(phone.length() == 10)){
				errorPhone = "Phone number not valid";
				request.setAttribute("errorPhone", errorPhone);
				pageNavigation(request, response, url);
				return;
			}
			
			if(!(zipcode.length() == 5)){
				errorZipCode = "Zipcode not valid";
				request.setAttribute("errorZipCode", errorZipCode);
				pageNavigation(request, response, url);
				return;
			}
		}
					
		userId = auth.enterNewUser(userName, password, userPrivilege);
		if(userId > 0)	{
			if(auth.enterUserName(userId, firstName, middleName, lastName, email, address1, address2, city, state, zipcode, phone))	{
				User user = auth.getUserById(userId);
				if(userPrivilege.equalsIgnoreCase("seller")){
					boolean result = auth.insertIntoApproval(userId);
					ec.sendSellerRegistrationEmail(user);
				}
				if(userPrivilege.equalsIgnoreCase("buyer")){
					ec.sendBuyerRegistrationEmail(user, userName);
				}
				msgRegisterStatus = "Account Created Successfully";
				url = "/Login.jsp";				
				request.setAttribute("msgFromServlet", msgRegisterStatus);
			}
			else	{
				msgRegisterStatus = "Username insert failed. Please try again.";
				request.setAttribute("registerStatus", msgRegisterStatus);
				auth.deleteUser(userId);
			}
			pageNavigation(request, response, url);
		}
		else if(userId == -1)	{
			msgRegisterStatus = "Create Account Failed. Please Try Again.";
			request.setAttribute("registerStatus", msgRegisterStatus);
			pageNavigation(request, response, url);
		}
		}
		
		try	{
		AuthDAO.DB_Close();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	private void pageNavigation(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(url);
		rDispatch.forward(request,response);
	}
}

