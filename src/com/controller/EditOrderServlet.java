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

import com.model.AuthDAO;
import com.model.OrderDAO;
import com.model.ViewOrder;

/**
 * Servlet implementation class EditOrderServlet
 */
@WebServlet("/EditOrderServlet")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		List<ViewOrder> result = null;
		String url;
		OrderDAO order = new OrderDAO();
		//System.out.println("I am here 1");
		result = order.viewOrder(AuthDAO.user_id);
		request.setAttribute("orderList", result);
		request.setAttribute("order_id", orderId);
		request.setAttribute("cancelBtn", "false");
		session.setAttribute("orderList", result);
		session.setAttribute("order_id", orderId);
		url = "/EditOrder.jsp";
			pageNavigation(request, response, url);
			return;
		}
	
	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}	
}
