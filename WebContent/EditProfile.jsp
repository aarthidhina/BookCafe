<%@ page import="com.model.User"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Profile</title>
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
			String msg = (String) request.getAttribute("msgFromServlet");
			if (msg == null)
				msg = "";

			out.println(msg);
	%>

	<h2>
		<span style="font-size: 36px">Edit Profile</span>
	</h2>
	<form action='ProfileServlet' method='post'>
		<table class="center">
			<tr>
				<td>User Name :</td>
				<td><b><%=user.getUserName()%></b></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type='text' name='firstName'
					value='<%=user.getFirstName()%>'></td>
			</tr>
			<tr>
				<td>Middle Name:</td>
				<td><input type='text' name='middleName'
					value='<%=user.getMiddleName()%>'></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type='text' name='lastName'
					value='<%=user.getLastName()%>'></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='email'
					value='<%=user.getEmail()%>'></td>
			</tr>
			<tr>
				<td>Address 1:</td>
				<td><input type='text' name='address1'
					value='<%=user.getAddress1()%>'></td>
			</tr>
			<tr>
				<td>Address 2:</td>
				<td><input type='text' name='address2'
					value='<%=user.getAddress2()%>'></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><input type='text' name='city' value='<%=user.getCity()%>'></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><input type='text' name='state'
					value='<%=user.getState()%>'></td>
			</tr>
			<tr>
				<td>Zip Code:</td>
				<td><input type='text' name='zipcode'
					value='<%=user.getZip()%>'></td>
			</tr>
			<tr>
				<td>Phone :</td>
				<td><input type='text' name='phone'
					value='<%=user.getPhone()%>'></td>
			</tr>
			<tr>
				<td>Reset Password :</td>
				<td><input type='password' name='password' value=''></td>
			</tr>
			<tr>
				<td>Confirm Password :</td>
				<td><input type='password' name='confirmPassword' value=''></td>
			</tr>
			<tr align='center'>
				<td colspan='2'><input type="hidden" name="userId"
					value="<%=user.getUser_id()%>"> <input type='submit'
					name='update' value='Update' /></td>
			</tr>
		</table>
	</form>
	<br>

	<%
		} else {
			out.println("<h4>You have to be logged in to access this page</h4>");
		}
	%>

	<%@include file="includes/footer.jsp"%>

</body>
</html>
