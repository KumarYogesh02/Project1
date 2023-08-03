<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>
<body>
	<h1 align="center">Welcome to the bank <%
	session =request.getSession();
	String s2=(String) session.getAttribute("bank");
	out.print(s2);
	%>
	</h1>
	<%
		session = request.getSession();
		String s1=(String) session.getAttribute("cust_name");
		out.println(s1+" Welcome to your account . Please select an operation to perform");
		
	%>
	<br>
	<br>
	<a href="CheckBalance">1.Check Balance</a>
	<br>
	<br>
	<a href="Changepwd.html">2.Change Password</a>
	<br>
	<br>
	<a href="Loan.jsp">3.Apply Loan</a>
	<br>
	<br>
	<a href="Transfer.html">4.Tranfer Money</a>
	<br>
	<br>
	<a href="Transection.java">5.Tranfer History</a>
	<br>
	<br>
	<a href="Logout.java">6.Logout</a>
	
</body>
</html>