<%@ page import="com.model.User"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Profile</title>
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

			User user = (User) session.getAttribute("user");
	%>
	<div id="hero">
		<h2>Profile</h2>
		<table class="center">
			<tr>
				<td><span style="font-size: 18px">User Name: </span></td>
				<td><b><%=user.getUserName()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">First Name: </span></td>
				<td><b><%=user.getFirstName()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Middle Name: </span></td>
				<td><b><%=user.getMiddleName()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Last Name: </span></td>
				<td><b><%=user.getLastName()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Email: </span></td>
				<td><b><%=user.getEmail()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Address 1 : </span></td>
				<td><b><%=user.getAddress1()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Address 2: </span></td>
				<td><b><%=user.getAddress2()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">City: </span></td>
				<td><b><%=user.getCity()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">State: </span></td>
				<td><b><%=user.getState()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Zip Code: </span></td>
				<td><b><%=user.getZip()%></b></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Phone: </span></td>
				<td><b><%=user.getPhone()%></b></td>
			</tr>
			<tr>
				<td><a href="EditProfile.jsp">Edit Profile </a></td>
				<td><a href="${pageContext.request.contextPath}/DeleteUserServlet">Delete Account </a></td>
				<td><a href="${pageContext.request.contextPath}/ViewOrderServlet">View Orders </a></td>
			</tr>
		</table>

		<br> <br>
	</div>
	<%
		} else {
			out.println("<h4>You have to be logged in to access this page</h4>");
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
