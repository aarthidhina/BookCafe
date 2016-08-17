package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AddBookDAO;
import com.model.AuthDAO;
import com.model.Cart;
import com.model.CartDao;
import com.model.EmailClient;
import com.model.OrderDAO;
import com.model.ViewOrder;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
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
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("Inside Checkout Servlet");
		String fullName = request.getParameter("full_name");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String phone_no = request.getParameter("phone_no");
		String card_no = request.getParameter("card_no");
		String name_on_card = request.getParameter("name_on_card");
		String expiry_date = request.getParameter("expiry_date");
		String security_code = request.getParameter("security_code");
		HttpSession session = request.getSession();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);

		String url = "/ViewOrder.jsp";
		String errorFullName = "";
		String errorAddress1 = "";
		String errorCity = "";
		String errorState = "";
		String errorZipcode = "";
		String errorPhoneNo = "";
		String errorCardNo = "";
		String errorNameOnCard = "";
		String errorExpiryDate = "";
		String errorSecurityCode = "";
		String error = "";

		if (request.getParameter("place_order") != null) {
			// System.out.println("Inside if place order button clicked");
			if (fullName.isEmpty() && address1.isEmpty() && address2.isEmpty()
					&& city.isEmpty() && state.isEmpty() && zipcode.isEmpty()
					&& phone_no.isEmpty() && card_no.isEmpty()
					&& name_on_card.isEmpty() && expiry_date.isEmpty()
					&& security_code.isEmpty()) {
				
				error = "Please fill the form";
				request.setAttribute("error", error);
				url = "/Checkout.jsp";
				pageNavigation(request, response, url);
				return;
			}

			else if (fullName.isEmpty() || address1.isEmpty() || city.isEmpty()
					|| state.isEmpty() || zipcode.isEmpty()
					|| phone_no.isEmpty() || card_no.isEmpty()
					|| name_on_card.isEmpty() || expiry_date.isEmpty()
					|| security_code.isEmpty()) {
				
				if (fullName.isEmpty())
					errorFullName = "Please enter your Full Name";

				if (address1.isEmpty())
					errorAddress1 = "Please enter your Address";

				if (city.isEmpty())
					errorCity = "Please enter your City";

				if (state.isEmpty())
					errorState = "Please enter your State";

				if (zipcode.isEmpty())
					errorZipcode = "Please enter your Zipcode";

				if (phone_no.isEmpty())
					errorPhoneNo = "Please enter your Phone Number";

				if (card_no.isEmpty())
					errorCardNo = "Please enter your Card Number";

				if (name_on_card.isEmpty())
					errorNameOnCard = "Please enter your Name on Card";

				if (expiry_date.isEmpty())
					errorExpiryDate = "Please enter your Expiry Date";

				if (security_code.isEmpty())
					errorSecurityCode = "Please enter your Security Code";

				request.setAttribute("errorFullName", errorFullName);
				request.setAttribute("errorAddress1", errorAddress1);
				request.setAttribute("errorCity", errorCity);
				request.setAttribute("errorState", errorState);
				request.setAttribute("errorZipcode", errorZipcode);
				request.setAttribute("errorPhoneNo", errorPhoneNo);
				request.setAttribute("errorCardNo", errorCardNo);
				request.setAttribute("errorNameOnCard", errorNameOnCard);
				request.setAttribute("errorExpiryDate", errorExpiryDate);
				request.setAttribute("errorSecurityCode", errorSecurityCode);

				url = "/Checkout.jsp";

				pageNavigation(request, response, url);
				return;

			} else {
				if (zipcode.length() < 5 || zipcode.length() > 6) {
					
					errorZipcode = "Please enter a valid Zipcode";
					request.setAttribute("errorZipcode", errorZipcode);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;

				} else if (phone_no.length() < 10 || phone_no.length() > 11) {
					
					errorPhoneNo = "Please enter a valid Phone Number";
					request.setAttribute("errorPhoneNo", errorPhoneNo);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;

				} else if (card_no.length() < 16 || card_no.length() > 17) {
					
					errorCardNo = "Please enter a valid Card Number";
					request.setAttribute("errorCardNo", errorCardNo);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;

				} else if (expiry_date
						.matches("^((0[1-9])|(1[0-2]))\\/(\\d{4})$") == false) {
					
					errorExpiryDate = "Please enter a valid Expiry Date";
					request.setAttribute("errorExpiryDate", errorExpiryDate);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;

				} else if (validateCardExpiry(expiry_date) == false) {
					
					errorExpiryDate = "Please enter a valid Expiry Date";
					request.setAttribute("errorExpiryDate", errorExpiryDate);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;

				} else if (security_code.length() < 3
						|| security_code.length() > 4) {
					
					errorSecurityCode = "Please enter a valid Security Code";
					request.setAttribute("errorSecurityCode", errorSecurityCode);
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;
				}
			}

			CartDao cartDao = new CartDao();
			OrderDAO orderDao = new OrderDAO();
			AddBookDAO bookDao = new AddBookDAO();
			EmailClient ec = new EmailClient();
			boolean result = false, result1 = false, result2 = false;
			int order_no;

			List<Cart> cartItems = new ArrayList<Cart>();
			Cart cart = new Cart();
			cartItems = cartDao.getCart();
			String orderStatus = "SUBMITTED";
			double tax = 0.0;
			double total_price = 0.0;
			double price_after_tax = 0.0;
			double shipping_cost = 0.0;
			int total_quantity = 0;
			int book_quantity = 0;
			int updated_book_quantity = 0;

			for (int i = 0; i < cartItems.size(); i++) {
				cart = cartItems.get(i);
				total_price += cart.getPrice() * cart.getQuantity();
				total_quantity += cart.getQuantity();
				shipping_cost += cartDao.getBookShippingCost(cart.getBook_id())
						* cart.getQuantity();
			}

			tax = total_price * 0.08;
			price_after_tax = total_price + tax + shipping_cost;
			order_no = orderDao.insertIntoOrder(AuthDAO.user_id, orderStatus,
					tax, price_after_tax, total_quantity);

			if (order_no > 0) {
				result = orderDao.insertIntoOrderShippingDetails(order_no,
						fullName, address1, address2, city, state, zipcode,
						phone_no);

				result1 = orderDao.insertIntoBuyerPaymentInfo(AuthDAO.user_id,
						card_no, expiry_date, security_code);

				for (int i = 0; i < cartItems.size(); i++) {
					cart = cartItems.get(i);
					result2 = orderDao.insertIntoOrderDetails(order_no,
							cart.getSeller_id(), cart.getBook_id(),
							cart.getQuantity(), cart.getPrice(),
							cartDao.getBookShippingCost(cart.getBook_id()),
							orderStatus);
				}

				if (result == true && result1 == true && result2 == true) {
					
					for(int i = 0; i < cartItems.size(); i++) {
						cart = cartItems.get(i);
						book_quantity = bookDao.getBookQuantity(cart.getBook_id());
						updated_book_quantity = book_quantity - cart.getQuantity();
						bookDao.updateBookQuantity(cart.getBook_id(), updated_book_quantity);						
					}
										
					for (int i = 0; i < cartItems.size(); i++) {
						cart = cartItems.get(i);
						cartDao.deleteCart(cart.getCart_id());
						session.removeAttribute("cartList");
					}
					AuthDAO auth = new AuthDAO();
					session.setAttribute("messageFromCheckoutServlet",
							"Order Successfully Placed");
					ec.sendOrderPlacedEmail(order_no, auth.getUserById(AuthDAO.user_id));
					List<ViewOrder> orderList = null;
					OrderDAO order = new OrderDAO();
					//System.out.println("I am here 1");
					orderList = order.viewOrder(AuthDAO.user_id);
					request.setAttribute("orderList", orderList);
					pageNavigation(request, response, url);
					//pageNavigation(request, response, url);
					return;
				} else {
					orderDao.deleteOrder(order_no);
					session.setAttribute("error",
							"There was an error placing your order. Please try again.");
					url = "/Checkout.jsp";
					pageNavigation(request, response, url);
					return;
				}
			} else {
				session.setAttribute("error",
						"There was an error placing your order. Please try again.");
				url = "/Checkout.jsp";
				pageNavigation(request, response, url);
				return;

			}
		}

	}

	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

	private boolean validateCardExpiry(String expiry_date) {
		String[] date = expiry_date.split("/");
		int expiry_month = Integer.parseInt(date[0]);
		int expiry_year = Integer.parseInt(date[1]);

		java.util.Calendar now = java.util.Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		// convert month from 0-11 to 1-12
		int month = now.get(Calendar.MONTH) + 1;

		if (expiry_year < year) {
			return false;
		}

		if (expiry_year == year) {
			if (expiry_month < month) {
				return false;
			}

		}
		return true;

	}
}
