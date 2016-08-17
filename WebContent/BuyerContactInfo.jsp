<%@ page import="com.model.User"%>
<%@ page import="java.util.ArrayList"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Contact Info</title>
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
		boolean noEntry = false;
		ArrayList<User> buyerList = (ArrayList) session.getAttribute("buyerList");			
		User user = new User();
		if(buyerList == null || buyerList.isEmpty()) {
			buyerList = new ArrayList<User>();
			noEntry = true;
		}

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
			if(userPrivilege.equalsIgnoreCase("admin")){
	%>
	<div id="hero">
		<%
			if(noEntry == false){
		%>
		<h2>Contact Information</h2>
<table class="center"  width="80%">
			<%
				for (int i = 0; i < buyerList.size(); i++) {
								user = buyerList.get(i);
								String fullname = user.getFirstName() + " " + user.getMiddleName() + " " + user.getMiddleName();
			%>
			<tr><td><%=i+1%></td></tr>
			<tr>
				<td bgcolor="#ddd"><span style="font-size: 18px">User Name: </span></td>
				<td bgcolor="#ddd"><b><%=user.getUserName()%></b></td>
				<td bgcolor="#ddd"><span style="font-size: 18px"> Name: </span></td>
				<td bgcolor="#ddd"><%=fullname%></td>
			</tr>
		<!-- 	<tr>
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
			</tr> -->
			<tr>
				<td bgcolor="#ddd"><span style="font-size: 18px">Email: </span></td>
				<td bgcolor="#ddd"><b><%=user.getEmail()%></b></td>
				<td bgcolor="#ddd"><span style="font-size: 18px">Phone: </span></td>
				<td bgcolor="#ddd"><b><%=user.getPhone()%></b></td>
			</tr>
			<tr>
				<td bgcolor="#ddd"><span style="font-size: 18px">Address 1 : </span></td>
				<td bgcolor="#ddd"><b><%=user.getAddress1()%></b></td>
				<td bgcolor="#ddd"><span style="font-size: 18px">Address 2: </span></td>
				<td bgcolor="#ddd"><b><%=user.getAddress2()%></b></td>
			</tr>
			<tr>
				<td bgcolor="#ddd"><span style="font-size: 18px">City: </span></td>
				<td bgcolor="#ddd"><b><%=user.getCity()%></b></td>
				<td bgcolor="#ddd"><span style="font-size: 18px">State/ZIP: </span></td>
				<td bgcolor="#ddd"><b><%=user.getState()%> / <%=user.getZip()%></b></td>
				<%-- <td bgcolor="#ddd"><span style="font-size: 18px">Zip Code: </span></td>
				<td bgcolor="#ddd"><b><%=user.getZip()%></b></td> --%>
			</tr>			
		
<%
		}%>
		</table>
		<br> <br>
	</div>
	<%} else {
			out.println("<h4>Contact details not available!</h4>");
		}
			} else {
		out.println("<h4>You do not have access to this page</h4>");
			}
		} else {
			out.println("<h4>You have to be logged in to access this page</h4>");
		}
	%>
<%@include file="includes/footer.jsp"%>
</body>
</html>
