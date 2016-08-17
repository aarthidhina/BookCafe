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
import com.model.EmailClient;
import com.model.OrderDAO;
import com.model.ViewOrder;

/**
 * Servlet implementation class EditOrderStatusServlet
 */
@WebServlet("/EditOrderStatusServlet")
public class EditOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrderStatusServlet() {
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
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		String status = request.getParameter("status");
		List<ViewOrder> result = null;
		String url;
		OrderDAO order = new OrderDAO();
		AuthDAO auth = new AuthDAO();
		EmailClient ec = new EmailClient();
		//System.out.println("Status : "+status);
		if(bookId==0){
			int runStatus = order.updateCompleteOrderStatus(orderId, status);			
			ec.sendCompleteOrderEmail(orderId, auth.getUserById(order.getBuyerId(orderId)), status);
			if(status.equalsIgnoreCase("shipped")){
				ec.sendReviewEmail(auth.getUserById(AuthDAO.user_id));
			}
		}
		else if(bookId>0)
		{
			int runStatus = order.updateProductOrderStatus(orderId, status, bookId);	
			ec.sendOrderStatusUpdateEmail(orderId, status, bookId,auth.getUserById(order.getBuyerId(orderId)));
		}
		result = order.viewOrder(AuthDAO.user_id);
		request.setAttribute("orderList", result);
		request.setAttribute("order_id", orderId);
		url = "/EditOrderStatus.jsp";
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
