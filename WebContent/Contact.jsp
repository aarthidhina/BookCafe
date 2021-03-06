<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Books</title>
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
		//session is used because if we use request, we need to forward that
		if (loggedIn == null)
			loggedIn = "";

		if (userPrivilege == null)
			userPrivilege = "";
		// Need to write login if try to access when already loggedIn

		if (userPrivilege.equalsIgnoreCase("seller")) {
	%>
	<%@include file="includes/header_seller.jsp"%>
	<%
		} else if (userPrivilege.equalsIgnoreCase("admin")) {
	%>
	<%@include file="includes/header_admin.jsp"%>
	<%
		}

		if (loggedIn.equals("true")) {
			if (userPrivilege.equalsIgnoreCase("admin")) {
	%>
	<div id="hero">
		<table class="center">
			<tr>
				<td><span style="font-size: 18px"><a href="ContactBuyer.jsp">Contact Buyer</a></span></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px"><a href="ContactSeller.jsp">Contact Seller</a></span></td>
			</tr>
		</table>

		<br> <br>
	</div>
	<%
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
