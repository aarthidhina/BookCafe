<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Checkout</title>
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

u {
	text-decoration: underline;
}

button.center {
	margin: auto;
	position: absolute;
	top: 0px;
	bottom: 0px;
	left: 0px;
	right: 0px;
}
</style>
</head>

<body>

	<%
		String loggedIn = (String) session.getAttribute("loggedIn");
		String error = (String) request.getAttribute("error");
		String userPrivilege = (String) session
				.getAttribute("userPrivilege");
		String errorFullName = (String) request
				.getAttribute("errorFullName");
		String errorAddress1 = (String) request
				.getAttribute("errorAddress1");
		String errorCity = (String) request.getAttribute("errorCity");
		String errorState = (String) request.getAttribute("errorState");
		String errorZipcode = (String) request.getAttribute("errorZipcode");
		String errorPhoneNo = (String) request.getAttribute("errorPhoneNo");
		String errorCardNo = (String) request.getAttribute("errorCardNo");
		String errorNameOnCard = (String) request
				.getAttribute("errorNameOnCard");
		String errorExpiryDate = (String) request
				.getAttribute("errorExpiryDate");
		String errorSecurityCode = (String) request
				.getAttribute("errorSecurityCode");

		if (loggedIn == null)
			loggedIn = "";

		if (userPrivilege == null)
			userPrivilege = "";

		if (error == null)
			error = "";

		if (errorAddress1 == null)
			errorAddress1 = "";

		if (errorFullName == null)
			errorFullName = "";

		if (errorCity == null)
			errorCity = "";

		if (errorState == null)
			errorState = "";

		if (errorZipcode == null)
			errorZipcode = "";

		if (errorPhoneNo == null)
			errorPhoneNo = "";

		if (errorCardNo == null)
			errorCardNo = "";

		if (errorNameOnCard == null)
			errorNameOnCard = "";

		if (errorExpiryDate == null)
			errorExpiryDate = "";

		if (errorSecurityCode == null)
			errorSecurityCode = "";

		if (loggedIn.equals("true")) {
			if (userPrivilege.equalsIgnoreCase("buyer")) {
	%>
	<%@include file="includes/header.jsp"%>
	<h2>
		<span class="center" style="font-size: 36px">Checkout</span>
	</h2>
	<h4><font color = 'red'>
		<%
			out.println(error);
		%></font>
	</h4>
	<form action='CheckoutServlet' method='post'>
		<table class="center">
			<tr>
				<td><span style="font-size: 18px">Full Name :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='full_name' /></td>
				<td><font color = 'red'><%out.println(errorFullName); %></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td class="u"><span style="font-size: 20px"><b>Shipping
							Address</b></span></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Shipping Address 1 :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='address1' /></td>
				<td><font color = 'red'><%out.println(errorAddress1); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Shipping Address 2: </span></td>
				<td><input type='text' name='address2' /></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">City :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='city' /></td>
				<td><font color = 'red'><%out.println(errorCity); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">State :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='state' /></td>
				<td><font color = 'red'><%out.println(errorState); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Zipcode :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='zipcode' /></td>
				<td><font color = 'red'><%out.println(errorZipcode); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Phone Number :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='phone_no' /></td>
				<td><font color = 'red'><%out.println(errorPhoneNo); %></font></td>
			</tr>
			<tr>
				<td class="u"><span style="font-size: 20px"><b>Payment
							Details</b></span></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Card Number :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='card_no' /></td>
				<td><font color = 'red'><%out.println(errorCardNo); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Name on Card :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='name_on_card' /></td>
				<td><font color = 'red'><%out.println(errorNameOnCard); %></font></td>
			</tr>
			<tr>
				<td><span style="font-size: 18px">Expiration Date :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='text' name='expiry_date' /></td>
				<td>(MM/YYYY)<font color = 'red'><%out.println(errorExpiryDate); %></font></td>			
			</tr>
			<tr>
				<td><span style="font-size: 18px">Security Code :<span
						style="color: red"><sup>*</sup></span></span></td>
				<td><input type='password' name='security_code' /></td>
				<td><font color = 'red'><%out.println(errorSecurityCode); %></font></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="place_order" value="Place Order"></td>
				<td></td>
			</tr>
		</table>
		<br>
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
