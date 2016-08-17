<%@ page import="com.model.User"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Approve Registration</title>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
<script>
	var __adobewebfontsappname__ = "dreamweaver"
</script>
<script src="http://use.edgefonts.net/aclonica:n4:default.js"
	type="text/javascript"></script>
<style>
h2 {
	text-align: center;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>

<body>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="com.model.Books"%>
	<%@ page import="com.model.Approve"%>
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
			
			ArrayList<Approve> result = new ArrayList<Approve>();
			//Books book = new Books();
			Approve approve=new Approve();
			result = (ArrayList) request.getAttribute("resultList");
			if(result.isEmpty())
			{
				result = new ArrayList<Approve>();
				//System.out.println("I am here JSP1");
			}
			//System.out.println("I am here JSP3");
			//User user = (User) session.getAttribute("user");
	%>
	<h2>
		<span style="font-size: 36px">Results</span>
	</h2>
	<table class="center" width="80%">

		<tr>
			<th width="100" align="center">UserID</th>
			<th width="100" align="left">UserName</th>
			<th width="100" align="left">Action</th>
			<th width="300" align="left">Select Approval/Decline</th>

		</tr>
		<%
			for (int i = 0; i < result.size(); i++) {
						approve = result.get(i);
						//System.out.println("I am here 1");
		%>
		<tr>
			<%
				if (approve.getStatus().equalsIgnoreCase("pending"))
									{
			%>
			<th width="100" align="center">
				<%
					out.println(approve.getuserid().toString());
				%>
			</th>
			<th width="100" align="left">
				<%
					out.println(approve.getusername().toString());
				%>
			</th>
			<th width="100" align="left">
				<%
					out.println(approve.getStatus().toString());
				%>
			</th>

			<th width="180" align="left">
				<form action='DeleteUserServlet' method='post'>
					<input type="radio" name="actiontotake" value="approve"
						checked>Approve<input type="radio" name="actiontotake"
						value="decline" checked>Decline
					<input type="hidden" name="user_idfordeletion"
						value="<%=approve.getuserid().toString()%>" /> <input
						type="submit" name="select" value="Submit" />
				</form>
			</th>
			<%
				}%>
		</tr>
		<%
			}
								}
		%>
	</table>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
