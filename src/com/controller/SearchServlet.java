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
import com.model.Books;
import com.model.SearchDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
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

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String dept = request.getParameter("dept");
		String error = "";
		String url = "/SearchResult.jsp";
		
		List<Books> result = null;
		System.out.println("title" + title);
		HttpSession session;
		session = request.getSession();

		SearchDAO search = new SearchDAO();

		if (title.isEmpty() & author.isEmpty() & isbn.isEmpty()
				& dept.isEmpty()) {
			error = "Please fill it atleast one field";
			session = request.getSession();
			session.setAttribute("error", error);
			url = "/Search.jsp";
			pageNavigation(request, response, url);
		}
		
		else{ 
			result = search.searchBook(title, author, isbn, dept);
			session = request.getSession();
			session.setAttribute("resultList", result);
			url = "/SearchResult.jsp";
			pageNavigation(request, response, url);
			try {
				AuthDAO.DB_Close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			AuthDAO.DB_Close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
