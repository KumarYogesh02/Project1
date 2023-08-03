package com.digit.javaProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	private ResultSet res2;
	private ResultSet res3;
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int custid=Integer.parseInt(req.getParameter("cust_id"));
		String bank_name = req.getParameter("bank_name");
		String sifsc = req.getParameter("ifsc_code");
		int saccno = Integer.parseInt(req.getParameter("sender_accno"));
        String rifsc = req.getParameter("receiver_ifsc");
        int raccno = Integer.parseInt(req.getParameter("receiver_accno"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        int pin = Integer.parseInt(req.getParameter("pin"));
        
        session = req.getSession();

        
        String url = "jdbc:mysql://localhost:3306/project1";

        String user = "root";

        String pwd = "yash8209@";
        
    	try {

            Class.forName("com.mysql.cj.jdbc.Driver");

              con = DriverManager.getConnection(url, user, pwd);
              
              pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and accno=? and pin=?");
              pstmt.setInt(1, custid);
              pstmt.setString(2,sifsc);
              pstmt.setInt(3, saccno);
              pstmt.setInt(4, pin);
              
              res = pstmt.executeQuery();
              
              if(res.next()==true) {
            	  pstmt=con.prepareStatement("select * from bankapp where ifsc_code=? and Accno=?");
            	  pstmt.setString(1, rifsc);
            	  pstmt.setInt(2, raccno);
            	 res2=pstmt.executeQuery();
            	 
            	 if(res2.next()==true) {
            		 pstmt=con.prepareStatement("select Balance from bankapp where Accno=?");
            		 pstmt.setInt(1, saccno);
            		 res3=pstmt.executeQuery();
            		 res3.next();
            		 int bal=res3.getInt(1);
            		 
            		 if(bal>amount) {
            			 pstmt= con.prepareStatement("update bankapp set balance = balance-? where accno=?");
            			 pstmt.setInt(1, amount);
            			 pstmt.setInt(2, saccno);
            			 int x1=pstmt.executeUpdate();
            			 if(x1>0) {
            				 pstmt=con.prepareStatement("update bankapp set balance=balance +? where accno=?");
            				 pstmt.setInt(1, amount);
            				 pstmt.setInt(2, raccno);
            				 int x2=pstmt.executeUpdate();
            				 if(x2>0) {
            					 
            					 Random r= new Random();
            					 int transid=(10000+r.nextInt(900000));
            					 pstmt=con.prepareStatement("insert into transferstates values (?,?,?,?,?,?,?,?)");
            					 pstmt.setInt(1, custid);
            					 pstmt.setString(2,bank_name);
            					 pstmt.setString(3, sifsc);
            					 pstmt.setInt(4, saccno);
            					 pstmt.setInt(5, raccno);
            					 pstmt.setInt(6, amount);
            					 pstmt.setString(7, rifsc);
            					 pstmt.setDouble(8, transid);
            					 int x3 = pstmt.executeUpdate();
            					 if(x3 > 0) {
            						 resp.sendRedirect("/Project1/TransferSuccess.html");
            					 }
            					 else {
            						 
            					 }
            				 }else {
            					 //insert data transaction data in transaction table
            					 System.out.println("fail due insert");
            				 }
            			 }else {
            				 //update balance in reciver side
            				 System.out.println("fail due update r");
            			 }
            		 }else {
            			 //update balance in user side
            			 System.out.println("fail due update user");
            		 }
            		 
            	 }else {
            		 //sufficient balance
            		 System.out.println("fail due insufficient");
            	 }
            	  
              }else {
            	  //reciver valid checl
            	  System.out.println("fail due reciever validation");
              }
             
	          
	          

	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}