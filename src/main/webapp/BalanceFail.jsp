<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance failure page</title>
</head>
<body>
	<h1>
	<% 
		session = request.getSession();
		out.println("Dear"+session.getAttribute("cust_name")+", your balance could not be fetch so try again");
	%>
	</h1>
	<a href="Home.jsp">Redirect</a>
</body>
</html>