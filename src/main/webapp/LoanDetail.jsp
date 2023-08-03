<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>loan detail page</title>
</head>
<body>
<%
 session = request.getSession();
	out.println(session.getAttribute("id"));
	out.println("<br>");
	out.println(session.getAttribute("type"));
	out.println("<br>");
	out.println(session.getAttribute("tenure"));
	out.println("<br>");
	out.println(session.getAttribute("interest"));
	out.println("<br>");
	out.println(session.getAttribute("desc"));
	
%>
<br>
<br>
<a href="Home.jsp">GO home</a>
</body>
</html>