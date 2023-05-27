package com.study.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	

	static String URL, USERNAME, PASSWORD;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		URL = rb.getString("url");
		USERNAME = rb.getString("user");
		PASSWORD = rb.getString("password");
		
	}

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
	    return (!resultSet.isBeforeFirst() && resultSet.getRow() == 0);
	}
	
	
}
