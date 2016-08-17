package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthDAO;
import com.model.OrderDAO;
import com.model.ViewOrder;
import com.model.EmailClient;
import com.model.User;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelOrderServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		int qty = 0;
		User user = new User();
		EmailClient ec = new EmailClient();
		AuthDAO auth = new AuthDAO();
		//System.out.println("I am here 1");
		if (!request.getParameter("order_id").isEmpty()
				&& !request.getParameter("book_id").isEmpty()) {
			//System.out.println("I am here 3");
			qty = Integer.parseInt(request.getParameter("qty"));
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			OrderDAO.cancelPartialOrder(orderId, book_id, qty);
			user = auth.getUserById(OrderDAO.getBuyerId(orderId));
			ec.sendCancelPartialOrderEmail(orderId, book_id, user);
			OrderDAO.updateOrderStatus(orderId);
		}
		if (request.getParameter("book_id").isEmpty()
				&& !request.getParameter("order_id").isEmpty()) {
			//System.out.println("I am here 4");
			int book_id = 0;
			ArrayList<ViewOrder> result = new ArrayList<ViewOrder>();
			ViewOrder order = new ViewOrder();
			result = (ArrayList) session.getAttribute("orderList");
			for (int i = 0; i < result.size(); i++) {
				order = result.get(i);
				if (orderId != order.getOrder_id())
					continue;
				book_id = order.getBook_id();
				qty = order.getQuantity();
				OrderDAO.cancelPartialOrder(orderId, book_id, qty);
				request.setAttribute("cancelBtn", "true");
				// OrderDAO.cancelOrder(orderId);
			}
			user = auth.getUserById(OrderDAO.getBuyerId(orderId));
			ec.sendCancelOrderEmail(orderId, user);
			OrderDAO.updateOrderStatus(orderId);
		}
		//System.out.println("I am here 2");
		List<ViewOrder> result = null;
		String url;
		OrderDAO order = new OrderDAO();
		// System.out.println("I am here 1");
		result = order.viewOrder(AuthDAO.user_id);
		request.setAttribute("orderList", result);
		request.setAttribute("order_id", orderId);
		session.setAttribute("orderList", result);
		session.setAttribute("order_id", orderId);
		url = "/EditOrder.jsp";
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
