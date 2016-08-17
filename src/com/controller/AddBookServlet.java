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

import com.model.AddBookDAO;
import com.model.AuthDAO;
import com.model.Books;
import com.model.EditBookDAO;
import com.model.SearchDAO;
import com.model.EmailClient;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("add") != null)
		{
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String dept = request.getParameter("dept");
			String description = request.getParameter("description");
			
			// Check the number values are not null before attempting to parse them as strings (to avoid number format exceptions).
			double price;
			if (request.getParameter("price") != "")
			{
				price = Double.parseDouble(request.getParameter("price"));
			} else {
				price = -1.0;
			}
			
			double shippingCost;
			if (request.getParameter("shippingCost") != "")
			{
				shippingCost = Double.parseDouble(request.getParameter("shippingCost"));
			} else {
				shippingCost = -1.0;
			}
			
			int quantity;
			if (request.getParameter("quantity") != "")
			{
				quantity = Integer.parseInt(request.getParameter("quantity"));
			} else {
				quantity = -1;
			}
			
			String image = request.getParameter("image");
			int sellerId = Integer.parseInt(request.getParameter("seller_id").toString());
			
			String msgFromServlet;
			String url;

			boolean result = false;
			
			if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || dept.isEmpty() ||
					description.isEmpty() || price <= 0.0 || shippingCost <= 0.0 || quantity <= 0) {
				
				msgFromServlet = "All Fields Are Required";

				request.setAttribute("msgFromServlet", msgFromServlet);
				url = "/AddBook.jsp";
				pageNavigation(request, response, url);
			}
			else{ 
				
				AddBookDAO add = new AddBookDAO();
				result = add.addBook(sellerId, title, author, isbn, dept, description, price, shippingCost, quantity, image);
				
				if (result == true)
				{
					msgFromServlet = "Book added successfully!";
				}
				else
				{
					msgFromServlet = "Error: Book not added";
				}
				
				request.setAttribute("msgFromServlet", msgFromServlet);
				url = "/AddBook.jsp";
				
				try {
					AuthDAO.DB_Close();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
				pageNavigation(request, response, url);
			}
		}
		// Transfer book info from search results to edit book jsp page.
		else if (request.getParameter("edit") != null)
		{
			
			int bookId = Integer.parseInt(request.getParameter("book_id"));
			
			Books book = new Books();
			EditBookDAO edit = new EditBookDAO();
			
			book = edit.getBookById(bookId);
			
			String url = "/EditBook.jsp";
			
			request.setAttribute("book_id", bookId);
			request.setAttribute("book_title", book.getTitle());
			request.setAttribute("book_author", book.getAuthor());
			request.setAttribute("book_isbn", book.getIsbn());
			request.setAttribute("book_price", book.getPrice());
			request.setAttribute("book_shippingCost", book.getShipping_cost());
			request.setAttribute("book_description", book.getDescription());
			request.setAttribute("book_dept", book.getDepartment());
			request.setAttribute("book_image", book.getImage());
			request.setAttribute("book_quantity", book.getQuantity());
			
			pageNavigation(request, response, url);
		}
		else if (request.getParameter("update") != null)
		{
			int bookId = Integer.parseInt(request.getParameter("book_id"));
			String bookTitle = request.getParameter("title");
			String bookAuthor = request.getParameter("author");
			String bookIsbn = request.getParameter("isbn");
			Double bookPrice = Double.parseDouble(request.getParameter("price"));
			Double bookShippingCost = Double.parseDouble(request.getParameter("shippingCost"));
			String bookDescription = request.getParameter("description");
			String bookDept = request.getParameter("dept");
			int bookQuantity = Integer.parseInt(request.getParameter("quantity"));
			String bookImage = request.getParameter("image");
			
			String msgFromServlet;
			String url;
			EmailClient ec = new EmailClient();

			boolean result = false;
			
			if (bookTitle.isEmpty() || bookAuthor.isEmpty() || bookIsbn.isEmpty() || bookDept.isEmpty() ||
					bookDescription.isEmpty() || bookPrice <= 0.0 || bookShippingCost <= 0.0 || bookQuantity < 0) {
				
				msgFromServlet = "All Fields Are Required";

				request.setAttribute("msgFromServlet", msgFromServlet);
				url = "/EditBook.jsp";
				pageNavigation(request, response, url);
			}
			else{ 
				
				EditBookDAO edit = new EditBookDAO();
				double oldPrice = edit.getBookPrice(bookId);
				result = edit.editBook(bookId, bookTitle, bookAuthor, bookIsbn, bookDept, bookDescription,
						bookPrice, bookShippingCost, bookQuantity, bookImage);
				
				if (result == true)
				{
					msgFromServlet = "Book updated successfully!";
					if(oldPrice!= -1 && bookPrice < oldPrice){
//						System.out.println("bookPrice < oldPrice");
						ec.sendUpdatePriceCartEmail(bookId);
						ec.sendUpdatePriceWishListEmail(bookId);
					}
				}
				else
				{
					msgFromServlet = "Error: Book not updated";
				}
				
				request.setAttribute("book_id", bookId);
				request.setAttribute("book_title", bookTitle);
				request.setAttribute("book_author", bookAuthor);
				request.setAttribute("book_isbn", bookIsbn);
				request.setAttribute("book_price", bookPrice);
				request.setAttribute("book_shippingCost", bookShippingCost);
				request.setAttribute("book_description", bookDescription);
				request.setAttribute("book_dept", bookDept);
				request.setAttribute("book_image", bookImage);
				request.setAttribute("book_quantity", bookQuantity);
				
				request.setAttribute("msgFromServlet", msgFromServlet);
				url = "/EditBook.jsp";
				
				try {
					AuthDAO.DB_Close();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
				pageNavigation(request, response, url);
			}
		}
		else if (request.getParameter("delete") != null)
		{
			int bookId = Integer.parseInt(request.getParameter("book_id"));
			boolean result = false;
			String msgFromServlet;
			String url;
			
			EditBookDAO delete = new EditBookDAO();
			result = delete.deleteBook(bookId);
			
			if (result == true)
			{
				msgFromServlet = "Book deleted successfully!";
			}
			else
			{
				msgFromServlet = "Error: Book not deleted";
			}
				
			request.setAttribute("msgFromServlet", msgFromServlet);
			url = "/Welcome.jsp";
			
			try {
				AuthDAO.DB_Close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			pageNavigation(request, response, url);
			
		}
		else if (request.getParameter("editDelete") != null)
		{
			int sellerId = Integer.parseInt(request.getParameter("seller_id"));
			HttpSession session = request.getSession();
			List<Books> result = null;
			SearchDAO search = new SearchDAO();
			
			result = search.getBooksBySellerId(sellerId);
			
			session.setAttribute("resultList", result);
			String url = "/SearchResult.jsp";
			
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
