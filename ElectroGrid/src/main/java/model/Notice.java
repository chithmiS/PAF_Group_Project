package model;
import java.sql.*;
import java.util.Date;

public class Notice {
	
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notice", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// insert notice
	public String insertNotice(String userId, String noticeSubject, String noticeBody) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into notices (`NoticeId`,`userId`,`noticeSubject`,`noticeBody`,`date`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userId);
			preparedStmt.setString(3, noticeSubject);
			preparedStmt.setString(4, noticeBody);
			Date date = new Date();
			preparedStmt.setDate(5, new java.sql.Date(date.getTime()));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the notice.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//view notice
	public String readNotices() 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 	{return "Error while connecting to the database for reading."; } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Notice Id</th><th>Admin Id</th><th>Notice Subject</th>" +
	 "<th>Notice Body</th>" + 
	 "<th>Published Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from notices"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String NoticeId = Integer.toString(rs.getInt("NoticeId")); 
	 String userId = rs.getString("userId"); 
	 String noticeSubject = rs.getString("noticeSubject"); 
	 String noticeBody = rs.getString("noticeBody"); 
	 String date = rs.getString("date"); 
	 
	 // Add into the html table
	 output += "<tr><td>" + NoticeId + "</td>"; 
	 output += "<td>" + userId + "</td>"; 
	 output += "<td>" + noticeSubject + "</td>"; 
	 output += "<td>" + noticeBody + "</td>"; 
	 output += "<td>" + date + "</td>"; 

	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='notice.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='NoticeId' type='hidden' value='" + NoticeId 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the notices."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	
	

	//update notice
	public String updateNotice(String  NoticeId, String userId, String noticeSubject, String noticeBody) 


	{ 
		 String output = ""; 
		 java.util.Date dateObj;
		 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 
		 // create a prepared statement
		 String query = "UPDATE notices SET userId=?,noticeSubject=?,noticeBody=?,date=? WHERE NoticeId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, userId); 
		 preparedStmt.setString(2, noticeSubject); 
		 preparedStmt.setString(3, noticeBody); 
		 
		 Date date = new Date();  
		 preparedStmt.setDate(4, new java.sql.Date(date.getTime()));
		 preparedStmt.setInt(5, Integer.parseInt(NoticeId)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the notice."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 

	//delete notice
		public String deleteNotice(String NoticeId) 
		 { 
			String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from notices where NoticeId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(NoticeId)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the notice."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
	//search notice
				public String searchNotices(String Notice_Id) 
				 { 
					String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
				 if (con == null) 
				 	{return "Error while connecting to the database for reading."; } 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Notice Id</th><th>Admin Id</th><th>Notice Subject</th>" +
				 "<th>Notice Body</th>" + 
				 "<th>Published Date</th>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
				 
				 String query = "select * from notices where NoticeId=?"; 
				
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 preparedStmt.setInt(1,Integer.parseInt(Notice_Id));
				 ResultSet rs = preparedStmt.executeQuery(); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
				 String NoticeId = Integer.toString(rs.getInt("NoticeId")); 
				 String userId = rs.getString("userId"); 
				 String noticeSubject = rs.getString("noticeSubject"); 
				 String noticeBody = rs.getString("noticeBody"); 
				 String date = rs.getString("date"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + NoticeId + "</td>"; 
				 output += "<td>" + userId + "</td>"; 
				 output += "<td>" + noticeSubject + "</td>"; 
				 output += "<td>" + noticeBody + "</td>"; 
				 output += "<td>" + date + "</td>"; 

				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='notice.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='NoticeId' type='hidden' value='" + NoticeId 
				 + "'>" + "</form></td></tr>"; 
				 } 
				 con.close(); 
				 
				 // Complete the html table
				 output += "</table>"; 
				 } 
				 catch (Exception e) 
				 { 
				 output = "Error while reading the notices."; 
				 System.err.println(e.getMessage()); 
				 } 
				 return output; 
				 } 
		
		
		
		
		


}