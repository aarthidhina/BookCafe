<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Wish List</title>
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
	<%@include file="includes/header.jsp"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="com.model.WishList"%>
	<%@ page import="com.model.Books"%>
	<%@ page import="com.model.WishlistDAO"%>
	<%
		//CartDao.getCart(session.getAttribute("userId").toString());	
		String loggedIn = (String) session.getAttribute("loggedIn");
		String msgAddToCart = (String) session.getAttribute("msgAddToCart");
		String wishListmessage = (String) session.getAttribute("wishListmessage");
		boolean noEntry = false;
		String userPrivilege = (String) session
		.getAttribute("userPrivilege");

		if (loggedIn == null)
			loggedIn = "";
		
		if(msgAddToCart == null)
			msgAddToCart = "";
		
		if(wishListmessage == null)
			wishListmessage = "";
		
		String color = "red";
		
		if(msgAddToCart.equalsIgnoreCase("Added to cart")){
			color = "green";
		}
		
		if(wishListmessage.equalsIgnoreCase("Updated Wish List"))
			color = "green";

		if (userPrivilege == null)
			userPrivilege = "";		
		
		if (loggedIn.equals("true")) {
			if(userPrivilege.equals("buyer")) {
			//WishlistDAO.getWishList();
			List<WishList> result = new ArrayList<WishList>();
			WishList wishlist = new WishList();
			WishlistDAO wishlistDao = new WishlistDAO();
			Books book = new Books();
			result = (ArrayList<WishList>) session.getAttribute("wishList");
			if(result == null || result.size()==0)
			{
				result = wishlistDao.getWishList();
				if(result == null || result.size()==0) {
					result = new ArrayList<WishList>();
					//System.out.println("No entry");
					noEntry = true;
				}
			}
	%>


	<h2>
		<span style="font-size: 36px">Wish List</span>
	</h2>
	<h4>
		<font color='<%=color%>'> <%
		out.println(msgAddToCart);
		session.removeAttribute("msgAddToCart");
 		out.println(wishListmessage);
		session.removeAttribute("wishListmessage");
 %></font>
	</h4>
	<% if(noEntry == false){ %>
	<table class="center" width="100%">

		<tr bgcolor="#ddd">
			<th width="40" align="center">S No</th>
			<th width="280" align="center">Book_Title</th>
			<th width="150" align="center">Seller Name</th>			
			<th width="80" align="center">Price ($)</th>
			<th width="120" align="center">Delete from Wish List</th>
			<th width="120" align="center">Add to Cart</th>
		</tr>
		<%
			//System.out.println("Size : 0");
					//System.out.println("Size : " + result.size());
					for (int i = 0; i < result.size(); i++) {
						wishlist = result.get(i);
		%>
		<tr bgcolor="#ddd">
			<th align="center">
				<%
					out.println(i + 1);
				%>
			</th>
			<th align="left">
				<%
					out.println(wishlist.getBookTitle());
				%>
			</th>
			<th align="center">
				<%
					out.println(wishlist.getSellerName());
				%>
			</th>
			<th align="right">
				<%
					out.println(wishlist.getPrice());
				%>
			</th>
			<th width="120" align="center">
				<form action='WishlistUpdateServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=wishlist.getBook_id()%>" />
					<input type="hidden" name="remove" value="delete" />
					<input type="submit" name="Delete" value="Delete from Wish List" />
				</form>
			</th>
			<th width="120" align="center">
				<form action='CartServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=wishlist.getBook_id()%>" />
					<input type="hidden" name="wishList" value="wishList" />
					<input type="submit" name="addToCart" value="Add To Cart" />
				</form>
			</th>
		</tr>
		<%
			}
		%>
	</table>
	<br>


	<%
		}
		else{
			out.println("<h4>No items added to the Wish List</h4>");
		}
		} else {
			out.println("<h4>You do not have access to this page</h4>");
		}
			} else {
		out.println("<h4>Please log in to access this page</h4>");
		
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>