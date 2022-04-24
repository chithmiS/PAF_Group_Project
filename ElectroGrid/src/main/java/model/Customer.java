package model;

import java.sql.*;

public class Customer {
	

	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		 

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 } 

		
		public String insertCustomer(String NIC , String firstname,String lastname, String homeNo,String street, String city,String phone, String account)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 String query = " insert into customers (`CustomerID`,`NIC`,`CustomerFirstName`,`CustomerLastName`,`HomeNo`,`Street`,`HomeCity`,`CustomerPhone`,`AccountNo`)"
		                + " values (?, ?, ?, ?, ?,?,?,?,?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, NIC);
		 preparedStmt.setString(3, firstname);
		 preparedStmt.setString(4, lastname);
		 preparedStmt.setString(5, homeNo);
		 preparedStmt.setString(6, street);
		 preparedStmt.setString(7, city);
		 preparedStmt.setInt(8, Integer.parseInt(phone));
		 preparedStmt.setString(9, account);
		// preparedStmt.setInt(9, Integer.parseInt(account));
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the Customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		


}
