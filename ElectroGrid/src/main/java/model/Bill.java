package model;

import java.sql.*;

public class Bill
{ 		
		//A common method to connect to the DB
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
		
		
		
		//Retrive bills
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
		
		
		//calculate monthly total amount
		public Double Amount(Double usage,Double rate) {
			
			return usage*rate;
		}
		
		
		
		
		
		
}
		
		