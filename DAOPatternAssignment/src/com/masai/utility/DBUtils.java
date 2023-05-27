package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	static String URL , USERNAME , PASSWORD;
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		URL = rb.getString("url");
		USERNAME = rb.getString("username");
		PASSWORD = rb.getString("password");
	}
	
	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//		Connection connection = null;
//		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//		return connection;
	}
	
	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		return (!rs.isBeforeFirst() && rs.getRow() == 0);
	}
}
