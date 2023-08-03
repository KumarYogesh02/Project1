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
@WebServlet("/Loan")
public class Loan extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s1=req.getParameter("loan");
		
		String url = "jdbc:mysql://localhost:3306/project1";

        String user = "root";

        String pwd = "yash8209@";
        
         session = req.getSession();
        

		try {

            Class.forName("com.mysql.cj.jdbc.Driver");

              con = DriverManager.getConnection(url, user, pwd);
              
              pstmt = con.prepareStatement("select * from applyloan where Lid=?");
              
              pstmt.setString(1, s1);
              
              res = pstmt.executeQuery();
              
              if(res.next()==true) {
            	  session.setAttribute("id",res.getInt("Lid"));
            	  session.setAttribute("type", res.getString("l_tpe"));
            	  session.setAttribute("tenure", res.getInt("tenure"));
            	  session.setAttribute("interest", res.getInt("interest"));
            	  session.setAttribute("desc", res.getString("description"));
            	  
            	  resp.sendRedirect("/Project1/LoanDetail.jsp");
            	  
              }
              else {
            	  System.out.println("value did not fetch");
            	  resp.sendRedirect("/Project1/LoanDetailFail.jsp");
              }
	          
	          

	}
		catch(Exception e) {
			e.printStackTrace();
		}
}
}
