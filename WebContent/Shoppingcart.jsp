<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Shopping Cart</title>
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
	<%@ page import="com.model.Cart"%>
	<%@ page import="com.model.Books"%>
	<%@ page import="com.model.CartDao"%>
	<%
		//CartDao.getCart(session.getAttribute("userId").toString());	
		String loggedIn = (String) session.getAttribute("loggedIn");
		String message = (String) session.getAttribute("message");
		boolean noEntry = false;
		String userPrivilege = (String) session
		.getAttribute("userPrivilege");

		if (loggedIn == null)
			loggedIn = "";
		
		if(message == null)
			message = "";
		
		String color = "red";
		
		if(message.equalsIgnoreCase("Updated cart"))
			color = "green";

		if (userPrivilege == null)
			userPrivilege = "";		
		
		if (loggedIn.equals("true")) {
			if(userPrivilege.equals("buyer")) {
			CartDao.getCart();
			ArrayList<Cart> result = new ArrayList<Cart>();
			Cart cart = new Cart();
			Books book = new Books();
			result = (ArrayList) session.getAttribute("cartList");
			if(result == null || result.size()==0)
			{
				result = new ArrayList<Cart>();
				//System.out.println("No entry");
				noEntry = true;
			}
	%>


	<h2>
		<span style="font-size: 36px">Shopping Cart</span>
	</h2>
	<h4>
		<font color='<%=color%>'> <%
 	out.println(message);
		session.removeAttribute("message");
 %></font>
	</h4>
	<% if(noEntry == false){ %>
	<table class="center" width="100%">

		<tr bgcolor="#ddd">
			<th width="40" align="center">S No</th>
			<th width="280" align="center">Book_Title</th>
			<th width="150" align="center">Seller Name</th>
			<th width="100" align="center">Quantity</th>
			<th width="80" align="center">Price ($)</th>
			<th width="80" align="center">Shipping cost ($)</th>
			<th width="80" align="center">Total</th>
			<th width="60" align="center">Update Cart</th>
		</tr>
		<%
			//System.out.println("Size : 0");
					//System.out.println("Size : " + result.size());
					for (int i = 0; i < result.size(); i++) {
						cart = result.get(i);
		%>
		<tr bgcolor="#ddd">
			<th align="center">
				<%
					out.println(i + 1);
				%>
			</th>
			<th align="left">
				<%
					out.println(cart.getBookTitle());
				%>
			</th>
			<th align="center">
				<%
					out.println(cart.getSellerName());
				%>
			</th>
			<th align="center">
				<form action='CartUpdateServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=cart.getBook_id()%>" />
					<input type="hidden" name="remove" value="" /> <input
						type="hidden" name="down" value="countdown" /> <input
						type="submit" name="reduce" value="-" />
				</form> <%
 	out.println(cart.getQuantity());
 %>
				<form action='CartUpdateServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=cart.getBook_id()%>" />
					<input type="hidden" name="remove" value="" /> <input
						type="hidden" name="down" value="" /> <input type="hidden"
						name="up" value="countup" /> <input type="submit" name="increase"
						value="+" />
				</form>
			</th>
			<th align="right">
				<%
					out.println(cart.getPrice());
				%>
			</th>
			<th align="right">
				<%
					out.println(cart.getShippingCost());
				%>
			</th>
			<th align="right">
				<%
					out.println(String.format("%.2f", (cart.getQuantity()
																		* cart.getPrice() + cart.getQuantity() * cart.getShippingCost())));
				%>
			</th>
			<th width="120" align="center">
				<form action='CartUpdateServlet' method='post'>
					<input type="hidden" name="book_id" value="<%=cart.getBook_id()%>" />
					<input type="hidden" name="remove" value="delete" /> <input
						type="submit" name="Delete" value="Delete" />
				</form>
			</th>
		</tr>
		<%
			}
		%>
	</table>
	<form action="Checkout.jsp" method="get">
		<input style="float: right" type="submit" value="Checkout">
	</form>
	<br>


	<%
		}
		else{
			out.println("<h4>No items added to the cart</h4>");
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