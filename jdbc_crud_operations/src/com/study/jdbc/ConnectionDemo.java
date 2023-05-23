package com.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_crud_operations", "root", "root");
			
			System.out.println("Connection Establish Succesfully...!");
			
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Connection Closed");
					e.printStackTrace();
				}
			}
		}
	}
}
