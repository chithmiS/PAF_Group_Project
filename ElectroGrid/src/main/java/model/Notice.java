package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

}