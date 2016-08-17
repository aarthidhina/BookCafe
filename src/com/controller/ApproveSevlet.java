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

import com.model.Approve;
import com.model.ApproveDAO;
import com.model.AuthDAO;

/**
 * Servlet implementation class ApproveSevlet
 */
@WebServlet("/ApproveSevlet")
public class ApproveSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		session = request.getSession();
		ApproveDAO search = new ApproveDAO();
		ArrayList<Approve> result = new ArrayList<Approve>();
		String url = "/ApproveRegistration.jsp";
		//System.out.println("I am here get 1");
		result = search.getSeller();
		if(result.isEmpty())
			System.out.println("I am here get 2");
		//session = request.getSession();
		request.setAttribute("resultList", result);
		//url = "/ApproveRegistration.jsp";
		pageNavigation(request, response, url);
		try {
			AuthDAO.DB_Close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	private void pageNavigation(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
				url);
		rDispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		session = request.getSession();
		ApproveDAO search = new ApproveDAO();
		ArrayList<Approve> result = new ArrayList<Approve>();
		String url = "/ApproveRegistration.jsp";
		System.out.println("I am here 1");
		result = search.getSeller();
		System.out.println("I am here 2");
		//session = request.getSession();
		session.setAttribute("resultList", result);
		//url = "/ApproveRegistration.jsp";
		pageNavigation(request, response, url);
		try {
			AuthDAO.DB_Close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
