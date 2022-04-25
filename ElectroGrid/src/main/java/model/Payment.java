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
			 output = "Error while inserting the payment.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	 }


public String readPayments()
{
	 String output = "";
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the HTML table to be displayed
		 
		 output = "<table border='1'><tr><th>Payment ID</th><th>Account Number</th>" +
		 "<th>Total Amount</th>" +
		 "<th>Payment Date</th>" +
		 "<th>Card Type</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVN</th>" ;
		
		
		 String query = "select * from payments";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next())
		 {
		 String payID = Integer.toString(rs.getInt("payID"));
		 String AccNumber = rs.getString("AccNumber");
		 String totalAmount = Double.toString(rs.getDouble("totalAmount"));
		 String payDate = rs.getString("payDate");
		 String cardType = rs.getString("cardType");
		 String cardNumber = rs.getString("cardNumber");
		 String cvn = rs.getString("cvn");
		 
		 // Add into the HTML table
		 
		 output += "<tr><td>" + payID + "</td>";
		 output += "<td>" + AccNumber + "</td>";
		 output += "<td>" + totalAmount + "</td>";
		 output += "<td>" + payDate + "</td>";
		 output += "<td>" + cardType + "</td>";
		 output += "<td>" + cardNumber + "</td>";
		 output += "<td>" + cvn + "</td>";
		
		 }
		 con.close();
		 
		 // Complete the HTML table
		 
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the payment.";
		 System.err.println(e.getMessage());
	 }
	 return output;
}

public String updatePayment(String payID, String AccNumber, String totalAmount, String payDate, String cardType, String cardNumber, String cvn)

{
		
	 String output = "";
	 
	 java.util.Date dateObj;
	 
	 try
	 {
		 Connection con = connect();
		 
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 // create a prepared statement
	 
	 String query = "UPDATE payments SET AccNumber=?,totalAmount=?,payDate=?,cardType=?,cardNumber=?,cvn=? WHERE payID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 
	 preparedStmt.setString(1, AccNumber);
	 preparedStmt.setDouble(2, Double.parseDouble(totalAmount));
	
	 
	 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	 dateObj = format.parse(payDate);
	 preparedStmt.setDate(3, new java.sql.Date(dateObj.getTime()));
	 
	 
	 preparedStmt.setString(4, cardType);
	 preparedStmt.setString(5, cardNumber);
	 preparedStmt.setString(6, cvn);
	 preparedStmt.setInt(7, Integer.parseInt(payID));
	 
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while updating the payment.";
		 System.err.println(e.getMessage());
	 }
	 return output;
}


public String deletePayment(String payID)
{
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 
	 // create a prepared statement
	 
	 String query = "delete from payments where payID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 
	 preparedStmt.setInt(1, Integer.parseInt(payID));
	 
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while deleting the payment.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }



//Retrieve one customer's payment details

public String readPayments(String Acc_Number)
{
	 String output = "";
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the HTML table to be displayed
		 
		 output = "<table border='1'><tr><th>Payment ID</th><th>Account Number</th>" +
		 "<th>Total Amount</th>" +
		 "<th>Payment Date</th>" +
		 "<th>Card Type</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVN</th>" +
		 "<th>Update</th><th>Remove</th></tr>";
		
		 String query = "select * from payments where AccNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 preparedStmt.setString(1, Acc_Number);
		 
		 ResultSet rs = preparedStmt.executeQuery();
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next())
		 {
		 String payID = Integer.toString(rs.getInt("payID"));
		 String AccNumber = rs.getString("AccNumber");
		 String totalAmount = Double.toString(rs.getDouble("totalAmount"));
		 String payDate = rs.getString("payDate");
		 String cardType = rs.getString("cardType");
		 String cardNumber = rs.getString("cardNumber");
		 String cvn = rs.getString("cvn");
		 
		 // Add into the HTML table
		 
		 output += "<tr><td>" + payID + "</td>";
		 output += "<td>" + AccNumber + "</td>";
		 output += "<td>" + totalAmount + "</td>";
		 output += "<td>" + payDate + "</td>";
		 output += "<td>" + cardType + "</td>";
		 output += "<td>" + cardNumber + "</td>";
		 output += "<td>" + cvn + "</td>";
		 
		 // Add buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='payments.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the HTML table
		 
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the payments.";
		 System.err.println(e.getMessage());
	 }
	 return output;
}


}
