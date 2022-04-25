package model;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Bill
{ 		
		//method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				// DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_bill", "root", "");
			}
			catch (Exception e)
				{e.printStackTrace();}
				return con;
		}
		
		
		
		//readBills method to view bills
		public String readBills()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border='1'>"
							+ "<tr>"
								+ "<th>Bill ID</th>"
								+ "<th>Account Number</th>"
								+ "<th>Name</th>" 
								+ "<th>Month</th>" 
								+ "<th>No of units Consumed</th>" 
								+ "<th>Rate per unit</th>" 
								+ "<th>Monthly Total Amount</th>"
								+ "<th>Date</th>"
								+ "<th>Update</th>"
								+ "<th>Remove</th>"
							+ "</tr>";
				
				String query = "select * from bills";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
				
					String bill_id = Integer.toString(rs.getInt("bill_id"));
					String acc_number = rs.getString("acc_number");
					String name = rs.getString("name");
					String month = rs.getString("month");
					String power_consumption = Double.toString(rs.getDouble("power_consumption"));
					String rate = Double.toString(rs.getDouble("rate"));
					String total_amount = Double.toString(rs.getDouble("total_amount"));
					String date = rs.getString("date");
					
					
					// Add into the html table
					output += "<tr><td>" + bill_id + "</td>";
					output += "<td>" + acc_number + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + month + "</td>";
					output += "<td>" + power_consumption + "</td>";
					output += "<td>" + rate + "</td>";
					output += "<td>" + total_amount + "</td>";
					output += "<td>" + date + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='bill_id' type='hidden' value='" + bill_id
							+ "'>" + "</form></td></tr>";
				}
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the bills.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		//method to calculate monthly total amount
		public Double calculateAmount(Double usage) {
			double total_amount;
			double amount1;
			double amount2;
			double amount3;
			double rate1 = 7;
			double rate2 = 10;
			
			if (usage <= 64 ) {
				
				total_amount = usage*rate1;	
			}else if( 96 >= usage && usage> 64) {
				
				
				amount1= 64*rate1;
				amount2= (usage-64)*rate2;
				total_amount = amount1+amount2   ;
				
			}else {
				amount1= 64*7;
				amount2= 32*10;
				amount3 = (usage-97)*20 ;
				total_amount = amount1 + amount2 + amount3;
				
				
			}
			
			return total_amount;
		}
		
		
		
		//insertBill method to insert data
		public String insertBill(String acc_number, String name,String month, Double power_consumption, Double rate, Double total_amount)
		{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				
				// create a prepared statement
				String query = " insert into bills(`bill_id`,`acc_number`,`name`,`month`,`power_consumption`,`rate`,`total_amount`,`date`)"
				+ " values (?, ?, ?, ?, ?,?,?,?)";
		
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, acc_number);
				preparedStmt.setString(3 ,name);
				preparedStmt.setString(4 ,month);
				preparedStmt.setDouble(5, power_consumption);
				preparedStmt.setDouble(6, rate);			
				preparedStmt.setDouble(7,total_amount);
				preparedStmt.setDate(8, new java.sql.Date(System.currentTimeMillis()));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Data inserted successfully";
				
			}
			catch (Exception e)
			{
				output = "Error while inserting the bill.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		
		//updateBill method to update bills
		public String updateBill(String bill_id, String acc_number, String name,String month, Double power_consumption, Double rate, Double total_amount,String date)
		{
			
			String output = "";
			java.util.Date dateObj;
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
					
				// create a prepared statement
				String query = "UPDATE bills SET acc_number=?,name=?,month=?,power_consumption=?,rate=?,total_amount=?, date=? WHERE bill_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
					
				// binding values
				preparedStmt.setString(1, acc_number);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, month);
				preparedStmt.setDouble(4, power_consumption);
				preparedStmt.setDouble(5, rate);
				preparedStmt.setDouble(6, total_amount);			
					
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				dateObj = format.parse(date);
				preparedStmt.setDate(7, new java.sql.Date(dateObj.getTime()));
				preparedStmt.setInt(8, Integer.parseInt(bill_id));
					
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Bill Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the bill.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		//Delete a bill
		public String deleteBill(String bill_id)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				String query = "delete from bills where bill_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(bill_id));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
		return output;
		}
				
		
			
		
		
		
		
		
}
		
		