<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Contact Us</title>
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
	<%		String loggedIn = (String) session.getAttribute("loggedIn");
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
	%>
	<h2>
		<span style="font-size: 36px"> 
		Contact Us
		</span>
	</h2>
	
		<table class="center">
			<tr>
				<td>Phone Number :</td>
				<td>7161009000</td>
			</tr>
			<tr>
				<td>Email :</td>
				<td>contactus@thebookcafe.com</td>
			</tr>
		</table>

	<%
		} else {
			out.println("<p>Please log in to access this page/p>");
		}
	%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
