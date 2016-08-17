<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
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
		String msgFromServlet = (String) request
				.getAttribute("msgFromServlet");
		String message = (String) request.getAttribute("message");
		String loggedIn = (String) session.getAttribute("loggedIn");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");

		if (msgFromServlet == null)
			msgFromServlet = "";

		if (message == null)
			message = "";

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

		if (!loggedIn.equals("true")) {
	%>
	<h2>
		<span style="font-size: 36px"> 
		<% out.println(loggedIn + " "); %>Login
		</span>
	</h2>
	<form action='LoginServlet' method='post'>
		<table class="center">
			<tr>
				<td colspan='2'>
					<h4>
						<font color='green'> 
						<% out.println(msgFromServlet); %>
						</font> 
						<font color='red'> 
						<% out.println(message); %>
						</font>
					</h4>
				</td>
			</tr>
			<tr>
				<td>UserName :</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type='password' name='password'></td>
			</tr>
			<tr align='center'>
				<td colspan='2'><input type='submit' name='login' value='Login' /></td>
			</tr>
			<tr>
				<td>Create an account?</td>
				<td><a href="Signup.jsp">Sign Up</a></td>
			</tr>
		</table>
	</form>
	<%
		} else {
			out.println("<h4>You are currently logged in</h4>");
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
