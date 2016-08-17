<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Activate Account</title>
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
		} else {
	%>
	<%@include file="includes/header.jsp"%>
	<%
		}

		if (loggedIn.equals("true")) {
	%>
	<table class="center">
		<tr>
			<td><h3>It looks like you have deleted your account. Do you want to activate it?</h3></td>
		</tr>
		<tr>
			<td><form action="ActivateAccountServlet" method="post">
					<input type="radio" name="activateAccount" value="yes" checked>Yes 
					<input type="radio" name="activateAccount" value="no" checked>No
					<br><input type="submit" name = "submit" value="Submit" />					
				</form></td>
		</tr>
	</table>

	<%
		} else {
			out.println("<h4>Please log in to access this page</h4>");
		}
	%>
	<br>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
