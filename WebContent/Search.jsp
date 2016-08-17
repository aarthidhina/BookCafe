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
	<%
		String loggedIn = (String) session.getAttribute("loggedIn");
		String error = (String) session.getAttribute("error");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");

		if (loggedIn == null)
			loggedIn = "";

		if (error == null)
			error = "";

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
	%>
	<h2>
		<span class="center" style="font-size: 36px">Search</span>
	</h2>
	<h4>
		<%
			out.println(error);
		%>
	</h4>
	<form action='SearchServlet' method='post'>
		<table class="center">
			<tr>
				<td><span style="font-size: 24px">Title: </span></td>
				<td><input type='text' name='title' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 24px">Author: </span></td>
				<td><input type='text' name='author' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 24px">ISBN: </span></td>
				<td><input type='text' name='isbn' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 24px">Department: </span></td>
				<td><input type='text' name='dept' /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="search" value="Search"></td>
			</tr>
		</table>

	</form>
	<%
		} else {
			out.println("<h4>Please log in to access this page</h4>");
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
