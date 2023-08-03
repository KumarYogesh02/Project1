<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan page</title>
</head>
<body>
<form action="Loan">

<h1 align="center">Loan page</h1>

<h5><%
session=request.getSession();
String s1=(String)session.getAttribute("cust_name");
out.println(s1+" you are eligible of those loan");
%></h5>

<lable>1.Home Loan</lable><br> <br>
<lable>2.Education Loan</lable><br> <br>
<lable>3.Vehicle Loan</lable><br> <br>
<lable>4.Gold Loan</lable><br> <br>
<lable>5.Personal Loan</lable><br> <br>

<input type="text" name="loan" placeholder="Enter number above loan"><br> <br>
<input type="submit" value="Apply loan">
</form>

</body>
</html>