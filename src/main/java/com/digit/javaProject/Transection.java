package com.digit.javaProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Transection.java")
public class Transection extends HttpServlet {
	
	private HttpSession session;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/project1";

        String user = "root";

        String pwd = "yash8209@";
        
         session = req.getSession();
         int accno=(int) session.getAttribute("accno");
    //     int raccno=(int)session.getAttribute("raccno");
         
         try {

             Class.forName("com.mysql.cj.jdbc.Driver");

               con = DriverManager.getConnection(url, user, pwd);
               
               pstmt = con.prepareStatement("select * from transferstates where sender_accno=? or receiver_accno=?");
               pstmt.setInt(1, accno);
               pstmt.setInt(2, accno);
               
               //ArrayList al= new ArrayList();
               res = pstmt.executeQuery();
               
               
               while(res.next()==true) {
            	 
            	 session.setAttribute("amount", res.getInt("amount"));
            	   
             	  
             	  resp.sendRedirect("/Project1/TransectionDetail.jsp");
             	  
               }
               
              
 	          
 	          

 	}
 		catch(Exception e) {
 			e.printStackTrace();
 		}
	}

}
