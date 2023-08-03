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
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet{
	
	private HttpSession session;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		int accno=(int) session.getAttribute("accno");
		
		String url = "jdbc:mysql://localhost:3306/project1";

        String user = "root";

        String pwd = "yash8209@";
        
  
        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

              con = DriverManager.getConnection(url, user, pwd);

             pstmt = con.prepareStatement("select balance from bankapp where accno=?");
             
            pstmt.setInt(1, accno);

            res = pstmt.executeQuery();

            if(res.next()==true) {
            	
            	session.setAttribute("balance", res.getInt("balance"));

                resp.sendRedirect("/Project1/Balance.jsp");

            }

            else {

                resp.sendRedirect("/Project1/BalanceFail.jsp");

            }

            

        }

        catch (Exception e) {

            e.printStackTrace();

        }
	}
}
