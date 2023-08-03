package com.digit.javaProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Changepassword")

public class Changepassword extends HttpServlet{
	
	private HttpSession session;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		session = req.getSession();
		int op=Integer.parseInt( (String) session.getAttribute("pwd"));
		int accno=(int)session.getAttribute("accno");
		int p1=Integer.parseInt(req.getParameter("opwd"));
		int p2=Integer.parseInt(req.getParameter("npwd"));
		int p3=Integer.parseInt(req.getParameter("cpwd"));
		
		String url = "jdbc:mysql://localhost:3306/project1";

        String user = "root";

        String pwd = "yash8209@";
        
        int x = 0;
		
		
			try {

	            Class.forName("com.mysql.cj.jdbc.Driver");

	              con = DriverManager.getConnection(url, user, pwd);
	              
	              if(op==p1&&p2==p3) {

	             pstmt = con.prepareStatement("update Bankapp set pin=? where accno=? and pin=?");
	             
	             pstmt.setInt(1, p2);
	             pstmt.setInt(2, accno);
	             pstmt.setInt(3, op);

	             x = pstmt.executeUpdate();
	            
	              }

	            if(x>0) {
	            	resp.sendRedirect("/Project1/PasswordChangeSuccess.html");

	            }

	            else {

	                resp.sendRedirect("/Project1/PasswordChangeFail.html");

	            }
	            
		}
			 catch (Exception e) {

		            e.printStackTrace();

		        }
		
	

}
}
