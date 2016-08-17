<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Sign Up</title>
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
		String status = (String) request.getAttribute("registerStatus");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");
		String errorUserName = (String) request
				.getAttribute("errorUserName");
		String msgCheckUserName = (String) request
				.getAttribute("msgCheckUserName");
		String errorFirstName = (String) request
				.getAttribute("errorFirstName");
		String errorLastName = (String) request
				.getAttribute("errorLastName");
		String errorPassword = (String) request
				.getAttribute("errorPassword");
		String errorConfirmPassword = (String) request
				.getAttribute("errorConfirmPassword");
		String errorAddress1 = (String) request
				.getAttribute("errorAddress1");
		String errorCity = (String) request.getAttribute("errorCity");
		String errorState = (String) request.getAttribute("errorState");
		String errorZipCode = (String) request.getAttribute("errorZipCode");
		String errorPhone = (String) request.getAttribute("errorPhone");
		String errorEmail = (String) request.getAttribute("errorEmail");

		if (loggedIn == null)
			loggedIn = "";

		if (status == null)
			status = "";

		if (errorUserName == null)
			errorUserName = "";

		String color = "red";

		if (msgCheckUserName == null)
			msgCheckUserName = "";
		else if (msgCheckUserName.equalsIgnoreCase("UserName Available"))
			color = "green";

		if (errorFirstName == null)
			errorFirstName = "";

		if (errorLastName == null)
			errorLastName = "";

		if (errorPassword == null)
			errorPassword = "";

		if (errorConfirmPassword == null)
			errorConfirmPassword = "";

		if (errorAddress1 == null)
			errorAddress1 = "";

		if (errorCity == null)
			errorCity = "";

		if (errorState == null)
			errorState = "";

		if (errorZipCode == null)
			errorZipCode = "";

		if (errorPhone == null)
			errorPhone = "";

		if (errorEmail == null)
			errorEmail = "";
		
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
		<span style="font-size: 36px">Sign Up</span>
	</h2>
	<form action='SignupServlet' method='post'>
		<table class="center">
			<tr align='center'>
				<td colspan='2'><font color='red'>
							<%
								out.println(status);
							%>
				</font></td>
			</tr>
			<tr>
				<td>User Name :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='username'></td>
				<td><font color='red'> <%
 	out.println(errorUserName);
 %>
				</font></td>

			</tr>
			<tr>
				<td><input type='submit' name='check'
					value='Check Availability' /></td>
				<td><font color='<%=color%>'> <%
 	out.println(msgCheckUserName);
 %>
				</font></td>

			</tr>

			<tr>
				<td>Password :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='password' name='password'></td>
				<td><font color='red'> <%
 	out.println(errorPassword);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Confirm Password :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='password' name='confirmpassword'></td>
				<td><font color='red'> <%
 	out.println(errorConfirmPassword);
 %>
				</font></td>
			</tr>
			<tr>
				<td>First Name :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='firstname'></td>
				<td><font color='red'> <%
 	out.println(errorFirstName);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Middle Name :</td>
				<td><input type='text' name='middlename'></td>
			</tr>
			<tr>
				<td>Last Name :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='lastname'></td>
				<td><font color='red'> <%
 	out.println(errorLastName);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Email :<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='email'></td>
				<td><font color='red'> <%
 	out.println(errorEmail);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Address 1:<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='address1'></td>
				<td><font color='red'> <%
 	out.println(errorAddress1);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Address 2:</td>
				<td><input type='text' name='address2'></td>
			</tr>
			<tr>
				<td>City:<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='city'></td>
				<td><font color='red'> <%
 	out.println(errorCity);
 %>
				</font></td>
			</tr>
			<tr>
				<td>State:<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='state'></td>
				<td><font color='red'> <%
 	out.println(errorState);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Zip Code:<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='zipcode'></td>
				<td><font color='red'> <%
 	out.println(errorZipCode);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Phone Number:<span
						style="color: red"><sup>*</sup></span></td>
				<td><input type='text' name='phone'></td>
				<td><font color='red'> <%
 	out.println(errorPhone);
 %>
				</font></td>
			</tr>
			<tr>
				<td>Select User Type:</td>
				<td><input type="radio" name="userPrivilege" value="buyer"
					checked>Buyer <input type="radio" name="userPrivilege"
					value="seller" checked>Seller <!-- 								<option value="userPrivilege">Buyer</option>
								<option value="userPrivilege">Seller</option> --></td>
			</tr>
			<tr align='center'>
				<td colspan='2'><input type='submit' name='signup'
					value='Sign-Up' /></td>
			</tr>
		</table>
	</form>
	<%
		} else {
	%>
	<h4>You are already a registered member</h4>
	<%
		}
	%>
	<br>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
