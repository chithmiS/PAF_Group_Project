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

		
		public String insertCustomer(String NIC , String firstname,String lastname, String homeNo,String street, String city,String phone, String account,String Password)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 String query = " insert into customers (`CustomerID`,`NIC`,`CustomerFirstName`,`CustomerLastName`,`HomeNo`,`Street`,`HomeCity`,`CustomerPhone`,`AccountNo`,`Password`)"
		                + " values (?, ?, ?, ?, ?,?,?,?,?,?)";
		 
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
		 preparedStmt.setString(10, Password);
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
		
		
		
		public String readCustomers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+"<th>Customer ID</th>"+"<th> NIC Number </th>"+"<th>Customer First Name </th>" +
		 "<th>Customer Last Name </th>" +"<th> Home No </th>" + "<th> Street </th>" + "<th> City </th>" +
		 "<th>Phone Number </th>" + "<th> Account Number </th>"+ "<th> Password </th>"+
		 "<th> Update </th> <th>Remove</th></tr>";

		 String query = "select * from customers";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String CustomerID = Integer.toString(rs.getInt("CustomerID"));
		 String NIC = rs.getString("NIC");
		 String CustomerFirstName = rs.getString("CustomerFirstName");
		 String CustomerLastName = rs.getString("CustomerLastName");
		 String HomeNo = rs.getString("HomeNo");
		 String Street = rs.getString("Street");
		 String City = rs.getString("HomeCity");
		 String CustomerPhone = Integer.toString(rs.getInt("CustomerPhone"));
		 String AccountNo = rs.getString("AccountNo");
		 String Password = rs.getString("Password");
		// String AccountNo = Integer.toString(rs.getInt("AccountNo"));
		
		 
		 // Add into the html table
		 output += "<tr><td>" + CustomerID + "</td>";
		 output += "<td>" + NIC + "</td>";
		 output += "<td>" + CustomerFirstName + "</td>";
		 output += "<td>" + CustomerLastName + "</td>";
		 output += "<td>" +  HomeNo + "</td>";
		 output += "<td>" +  Street + "</td>";
		 output += "<td>" +  City + "</td>";
		 output += "<td>" + CustomerPhone + "</td>";
		 output += "<td>" + AccountNo + "</td>";
		 output += "<td>" + Password + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + CustomerID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Customers.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		public String updateCustomer(String CustomerID,String NIC , String firstname,String lastname, String homeNo,String street, String city,String phone, String account,String Password)

		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 
			 
			 // create a prepared statement
			 String query = "UPDATE customers SET NIC=?,CustomerFirstName=?,CustomerLastName=?,HomeNo=?,Street=?,HomeCity=?,CustomerPhone=? ,AccountNo=?,Password=? WHERE CustomerID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			
			
			 preparedStmt.setString(1, NIC);
			 preparedStmt.setString(2, firstname);
			 preparedStmt.setString(3, lastname);
			 preparedStmt.setString(4, homeNo);
			 preparedStmt.setString(5, street);
			 preparedStmt.setString(6, city);
			 preparedStmt.setInt(7, Integer.parseInt(phone));
			// preparedStmt.setInt(8, Integer.parseInt(account));
			 preparedStmt.setString(8, account);
			 preparedStmt.setString(9, Password);
			 preparedStmt.setInt(10, Integer.parseInt(CustomerID));
			 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the Customer."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
		
		
		public String deleteCustomer(String CustomerID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from customers where CustomerID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(CustomerID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the Customer."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 

		
		//Search Method 
		
		public String searchCustomers(String nic)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+"<th>Customer ID</th>"+"<th> NIC Number </th>"+"<th>Customer First Name </th>" +
		 "<th>Customer Last Name </th>" +"<th> Home No </th>" + "<th> Street </th>" + "<th> City </th>" +
		 "<th>Phone Number </th>" + "<th> Account Number </th>"+ "<th> Password  </th>"+
		 "<th>Remove</th></tr>";

		 String query = "select * from customers where NIC=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 preparedStmt.setString(1, nic);
		 ResultSet rs = preparedStmt.executeQuery();
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String CustomerID = Integer.toString(rs.getInt("CustomerID"));
		 String NIC = rs.getString("NIC");
		 String CustomerFirstName = rs.getString("CustomerFirstName");
		 String CustomerLastName = rs.getString("CustomerLastName");
		 String HomeNo = rs.getString("HomeNo");
		 String Street = rs.getString("Street");
		 String City = rs.getString("HomeCity");
		 String CustomerPhone = Integer.toString(rs.getInt("CustomerPhone"));
		 String AccountNo = rs.getString("AccountNo");
		 String Password = rs.getString("Password");
		
		
		 
		 // Add into the html table
		 output += "<tr><td>" + CustomerID + "</td>";
		 output += "<td>" + NIC + "</td>";
		 output += "<td>" + CustomerFirstName + "</td>";
		 output += "<td>" + CustomerLastName + "</td>";
		 output += "<td>" +  HomeNo + "</td>";
		 output += "<td>" +  Street + "</td>";
		 output += "<td>" +  City + "</td>";
		 output += "<td>" + CustomerPhone + "</td>";
		 output += "<td>" + AccountNo + "</td>";
		 output += "<td>" + Password + "</td>";
		
		 
		 // buttons
		 output += 
		  "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + CustomerID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Customers.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
		
		
		


}
