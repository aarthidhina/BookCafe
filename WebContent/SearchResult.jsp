<%@ page import="com.model.User"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Search</title>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
<script>
	var __adobewebfontsappname__ = "dreamweaver"
</script>
<script src="http://use.edgefonts.net/aclonica:n4:default.js"
	type="text/javascript"></script>
<style>
h2,h4 {
	text-align: center;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>

<body>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="com.model.Books"%>
	<%
		String loggedIn = (String) session.getAttribute("loggedIn");
		String userPrivilege = (String) session.getAttribute("userPrivilege");
		String message = (String) session.getAttribute("msgAddToCart");
		String msgFromWishList = (String) session.getAttribute("msgAddToWishList");
		boolean noEntry = false;	
		String color = "red";
		if (loggedIn == null)
			loggedIn = "";
				
		if (userPrivilege == null)
					userPrivilege = "";
		if(message == null)
			message = "";
		
		if(msgFromWishList == null)
			msgFromWishList = "";
		
		if(message.equalsIgnoreCase("Added to cart")){
			color = "green";
		}
		
		if(msgFromWishList.equalsIgnoreCase("Added to Wish List")){
			color = "green";
		}
		
		if (userPrivilege.equalsIgnoreCase("admin")) {
			%>
			<%@include file="includes/header_admin.jsp"%>
			<%
				} else if (userPrivilege.equalsIgnoreCase("seller")) {
			%>
			<%@include file="includes/header_seller.jsp"%>
			<%
				} else if (userPrivilege.equalsIgnoreCase("buyer")) {
			%>
			<%@include file="includes/header.jsp"%>
			<%
				} else {
			%>
			<%@include file="includes/header_login.jsp"%>
			<%
				}

		if (loggedIn.equals("true")) {
			ArrayList<Books> result = new ArrayList<Books>();
			Books book = new Books();
			result = (ArrayList) session.getAttribute("resultList");
			if(result == null || result.size()==0)
			{
				result = new ArrayList<Books>();
				System.out.println("No entry");
				noEntry = true;
			}
			
			User user = (User) session.getAttribute("user");
	%>
	<h2>
		<span style="font-size: 36px">Results</span>
	</h2>
		<h4>
		<font color='<%=color%>'> <%
 	out.println(message);
	session.removeAttribute("msgAddToCart");
	out.println(msgFromWishList);
	session.removeAttribute("msgAddToWishList");
 %></font>
	</h4>
	<% if(noEntry == false){ %>
	<table class="center" width="100%">

		<tr bgcolor="#aaa">
			<th width="100" align="center">Image</th>
			<th width="350" align="center">Title</th>
			<th width="280" align="center">Author</th>
			<% if (userPrivilege.equalsIgnoreCase("buyer")||userPrivilege.equalsIgnoreCase("admin")){%>
			<th width="280" align="center">Seller</th><%} %>
			<th width="150" align="center">Price ($)</th>
			<th width="150" align="center">Shipping cost ($)</th>
			<% if (userPrivilege.equalsIgnoreCase("seller")||userPrivilege.equalsIgnoreCase("admin")){%>
			<th width="150" align="center">Edit</th>
			<th width="150" align="center">Delete</th><%} %>
			<% if (userPrivilege.equalsIgnoreCase("buyer")){%>
			<th width="150" align="center">Add to cart</th>
			<th width="150" align="center">Add to Wish List</th>
			<%} %>

		</tr>
		<%
			for (int i = 0; i < result.size(); i++) {
								        book = result.get(i);
		%>
		<tr bgcolor="#ddd">
			<%
				if (userPrivilege.equalsIgnoreCase("admin") || userPrivilege.equalsIgnoreCase("buyer") ||
											(userPrivilege.equalsIgnoreCase("seller") && user.getUser_id() == Integer.parseInt(book.getSeller_id())))
				{
			%>
			<td width="150"><img align="left" src="<%=book.getImage()%>"
				alt="Image Not Available" width=100 height=100></td>
			<td width="350" align="center">
				<%
					out.println(book.getTitle().toString());
				%>
				<form action='BookReviewViewServlet' method='get'>
					<input type="hidden" name="book_id_view" value="<%=book.getBook_id().toString()%>" />
					<input type="submit" name="Book Review" value="Book Review" />
				</form>
			</td>
			<td width="280" align="center">
				<%
					out.println(book.getAuthor().toString());
				%>
			</td>
			<% if (userPrivilege.equalsIgnoreCase("buyer")||userPrivilege.equalsIgnoreCase("admin")){%>
			<td width="280" align="center">
				<%
					out.println(book.getSeller_name());
				%>
				
				<br><a href = "mailto:<%=book.getSellerEmail().toString()%>">Contact Seller</a>
				<form action='SellerReviewViewServlet' method='get'>
					<input type="hidden" name="seller_id_view" value="<%=book.getSeller_id().toString()%>" />
					<input type="submit" name="Seller Review" value="Seller Review" />
				</form>
			</td><%} %>
			<td width="150" align="right">
				<%
					out.println(book.getPrice());
				%>
			</td>
			<td width="150" align="right">
				<%
					out.println(book.getShipping_cost());
				%>
			</td>
			<%
				}
				if (userPrivilege.equalsIgnoreCase("buyer")) 
				{
			%>
			<th width="120" align="center">
				<form action='CartServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
					<input type="submit" name="addToCart" value="Add To Cart" />
				</form>
			</th>
			<th width="120" align="center">
				<form action='WishListServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
					<input type="submit" name="addToWishList" value="Add To WishList" />
				</form>
			</th>
			<%
				}
				if (userPrivilege.equalsIgnoreCase("admin") ||
						(userPrivilege.equalsIgnoreCase("seller") && user.getUser_id() == Integer.parseInt(book.getSeller_id())))
				{
			%>
			<td align="center">
				<form action='AddBookServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
					<input type="submit" name="edit" value="Edit Book"/>
				</form>
			</td>
			<td align="center">
				<form action='AddBookServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
					<input type="submit" name="delete" value="Delete" />
				</form>
			</td>

			<%
				}
			%>
		</tr>
		
		<%	}}
		else{
				out.println("<h4>No matchin book found</h4>");
			}
			} else {
				out.println("<h4>Please log in to access this page</h4>");
			}
		%>
		</table>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
