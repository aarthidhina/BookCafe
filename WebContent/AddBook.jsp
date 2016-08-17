<%@ page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Book</title>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
<script>
	var __adobewebfontsappname__ = "dreamweaver";
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

button.center {
	margin: auto;
	position: absolute;
	top: 0px;
	bottom: 0px;
	left: 0px;
	right: 0px;
}
</style>
</head>
<body>
	<%
		String msgFromServlet = (String) request
				.getAttribute("msgFromServlet");
		String message = (String) request.getAttribute("message");
		String loggedIn = (String) session.getAttribute("loggedIn");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");

		// Check if userId is null first so we don't get a null pointer exception.
		int userId;
		if (session.getAttribute("userId") != null) {
			userId = Integer.parseInt(session.getAttribute("userId")
					.toString());
		} else {
			userId = -1;
		}

		if (msgFromServlet == null)
			msgFromServlet = "";

		if (message == null)
			message = "";

		if (loggedIn == null)
			loggedIn = "";

		if (userPrivilege == null)
			userPrivilege = "";
		String color = "red";
		
		if(msgFromServlet.equalsIgnoreCase("Book added successfully!")){
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
			if (userPrivilege.equalsIgnoreCase("admin")
					|| userPrivilege.equalsIgnoreCase("seller")) {

			%>
			<h4><font color='<%=color%>'>
			<%	out.println(msgFromServlet);
			request.removeAttribute("msgFromServlet");
	%> </font></h4>

	<h2>
		<span class="center" style="font-size: 36px">Add Book</span>
	</h2>
	<form action='AddBookServlet' method='post'>
		<table class="center">
			<tr>
				<td><span style="font-size: 18px">Title:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='title' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Author:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='author' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18">ISBN:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='isbn' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Department:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='dept' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Description:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='description' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Price:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='price' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Shipping Cost:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='shippingCost' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Quantity:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='quantity' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Image:<span
						style="color: red"><sup>*</sup></span> </span></td>
				<td><input type='text' name='image' /></td>
			<!-- 	<td><input style="float: center" type="button" value="Upload" /></td> -->
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="seller_id" value="<%=userId%>">
					<input type="submit" name="add" value="Add Book"></td>
				<td></td>
			</tr>
		</table>
		<br>

	</form>

	<%
		} else {
			out.println("<p>You do not have access to this page</p>");
		}
		} else {
			out.println("<p>Please log in to access this page</p>");
		}
	%>

	<%@include file="includes/footer.jsp"%>

</body>
</html>