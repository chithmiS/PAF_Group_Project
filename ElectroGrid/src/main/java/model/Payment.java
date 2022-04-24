package model;

import java.sql.*;
import java.text.SimpleDateFormat; 

public class Payment {
	
	 //Connect to the DB
	 private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	
	 //Provide the correct details: DBServer/DBName, user name, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;

}

public String insertPayment(String AccNumber, String totalAmount, String payDate, String cardType, String cardNumber, String cvn)
	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 
		 java.util.Date dateObj;
		 
		 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
		 
			 // create a prepared statement
		 
			 String query = " insert into payments(`payID`,`AccNumber`,`totalAmount`,`payDate`,`cardType`,`cardNumber`,`cvn`)"
			 + " values (?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, AccNumber);
			 preparedStmt.setDouble(3, Double.parseDouble(totalAmount));
			
			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 dateObj = format.parse(payDate);
			 preparedStmt.setDate(4, new java.sql.Date(dateObj.getTime()));
			 
			 
			 preparedStmt.setString(5, cardType);
			 preparedStmt.setString(6, cardNumber);
			 preparedStmt.setString(7, cvn);
			 
			 // execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
		 }
		 
		 catch (Exception e)
		 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	 }

	 
}
