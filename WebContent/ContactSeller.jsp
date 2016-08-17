<%@ page import="com.model.User"%>
<%@ page import="java.util.ArrayList"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Contact Buyer</title>
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
	<%
		String loggedIn = (String) session.getAttribute("loggedIn");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");


		if (loggedIn == null)
			loggedIn = "";

		if (userPrivilege == null)
			userPrivilege = "";			

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
			if (userPrivilege.equalsIgnoreCase("admin")) {
	%>
	<h2>
		<span style="font-size: 36px"> Contact Seller </span>
	</h2>

	<form action='ContactSellerServlet' method='post'>
		<table class="center">
			<tr>
				<td><span style="font-size: 24px">Enter Seller User Name: </span></td>
				<td><input type='text' name='username' /></td>
			</tr>			
			<tr>
				<td></td>
				<td><input type="submit" name="search" value="Search"></td>
			</tr>
		</table>

	</form>
	<%
		} else {
			out.println("<h4>You do not have access to this page</h4>");
		}
		} else {
			out.println("<p>Please log in to access this page/p>");
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
