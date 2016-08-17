<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Review Seller</title>
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
		int seller_id = Integer.parseInt(request.getAttribute("seller_id").toString());
		if (loggedIn == null)
			loggedIn = "";
		if (seller_id<=0)
			seller_id = 0;
		
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
			if (userPrivilege.equalsIgnoreCase("buyer")) {
	%>
	<h2>
		<span class="center" style="font-size: 36px">Review Seller</span>
	</h2>
	<h4>
		<%
			out.println(error);
		%>
	</h4>
	<form action='ReviewServlet' method='post'>
		<table class="center">
			<tr>
				<td><span style="font-size: 24px">Rating: </span></td>
				<td><input type="radio" name="rating" value="1">1 
					<input type="radio" name="rating" value="2">2 
					<input type="radio" name="rating" value="3">3 
					<input type="radio" name="rating" value="4">4 
					<input type="radio" name="rating" value="5">5</td>
			</tr>
			<tr>
				<td><span style="font-size: 24px">Review: </span></td>
				<td><textarea name="review_text" rows="4" cols="50"
						maxlength="300"></textarea></td>
			</tr>
			<tr>
				<td>
	            <input type="hidden" name="seller_id" value="<%=seller_id%>" />
				<input type="submit" name="SellerReview" value="Submit Review"></td>
			</tr>
		</table>

	</form>
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
