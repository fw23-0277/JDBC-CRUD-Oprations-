package com.study.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MobileCrudOpration {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class Not Loded...!");
			System.exit(1);
		}
	}

	static Connection createConnection() {
		String URL = "jdbc:mysql://localhost:3306/jdbc_crud_operations";
		String USERNAME = "root";
		String PASSWORD = "root";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	static void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Database Connection Not Closed...!");
			}
		}
	}
	
	static void CreateTableMobile() {
		Connection connection = createConnection();
		String createTable = "CREATE TABLE IF NOT EXISTS MOBILE (id INT(8) PRIMARY KEY AUTO_INCREMENT ,"
				+ "model_no VARCHAR(4) UNIQUE NOT NULL,"
				+ "company VARCHAR(4) NOT NULL,"
				+ "price INT(6) NOT NULL,"
				+ "MFGdate DATE);";
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(createTable);
		} catch (SQLException e) {
			System.out.println("Mobile Table is Not Created...!");
		}finally {
			closeConnection(connection);
		}
	}
	
	static void addMobile(Scanner sc) {
		System.out.print("Enter Mobile Model No. : ");
		String modelNo = sc.next();
		System.out.print("Enter Company Name : ");
		String companyName = sc.next();
		System.out.print("Enter Mobile Price : ");
		int price = sc.nextInt();
		System.out.print("Mobile Manufacturing Date [ YYYY-MM-DD ] : ");
		String mfgDate = sc.next();
		
		String addQuery = "INSERT INTO MOBILE (model_no,company,price,MFGdate)VALUES(?,?,?,?)";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(addQuery);
			
			prepareStatement.setString(1, modelNo);
			prepareStatement.setString(2, companyName);
			prepareStatement.setInt(3, price);
			prepareStatement.setString(4, mfgDate);
			
			if(prepareStatement.executeUpdate() > 0) {
				System.out.println("Mobile Details Added Successfully...!");
			}else {
				System.out.println("Unabale Add to Mobile Details into Database...!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnection(connection);
		}	
	}
	
	static void updateMobile(Scanner sc) {
		System.out.print("Enter Mobile Model No. : ");
		String modelNo = sc.next();
		System.out.print("Enter Company Name : ");
		String companyName = sc.next();
		System.out.print("Enter Mobile Price : ");
		int price = sc.nextInt();
		System.out.print("Mobile Manufacturing Date [ YYYY-MM-DD ] : ");
		String mfgDate = sc.next();
		
		String updateMobile = "UPDATE MOBILE SET company = ? , price = ? , MFGdate = ? WHERE model_no = ?";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(updateMobile);
				
				prepareStatement.setString(1,companyName);
				prepareStatement.setInt(2, price);
				prepareStatement.setString(3, mfgDate);
				prepareStatement.setString(4, modelNo);
				
				if(prepareStatement.executeUpdate() > 0) {
					System.out.println("Mobile Details Updated Sucsessfully...!");
				}else {
					System.out.println("Mobile Details Not Upadted...!");
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	static void deleteMobile(Scanner sc) {
		System.out.print("Enter Mobile Model No. : ");
		String modelNo = sc.next();
		
		String deleteMobile = "DELETE FROM MOBILE WHERE model_no = ?";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(deleteMobile);
		
			prepareStatement.setString(1, modelNo);
			
			if(prepareStatement.executeUpdate() > 0) {
				System.out.println("Mobile Details Deleted Successfully...!");
			}else {
				System.out.println("Mobile Details Not Exist...!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static void viewMobiles() {
		String viewMobile = "SELECT * FROM MOBILE ORDER BY price ASC;";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(viewMobile);
			ResultSet rs = prepareStatement.executeQuery();
			
			if (rs.next()) {
				System.out.println("Model_No " + " Company_Name" + "  Price" + " Manufacuring_Date");
				do {

					System.out.println("  " + rs.getString(2) + "\t\t" + rs.getString(3)+ "\t" +  rs.getInt(4) + "\t" +  rs.getString(5));	
				}while(rs.next());
			}else {
				System.out.println("Oops...! Mobile Not Found.");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void searchMobile(Scanner sc) {
		System.out.print("Enter Mobile Model No. : ");
		String modelNo = sc.next();
		
		String searchMobile = "SELECT * FROM MOBILE WHERE model_no = ?";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(searchMobile);
			prepareStatement.setString(1, modelNo);
			ResultSet rs = prepareStatement.executeQuery();
			
			if (rs.next()) {
				System.out.println("Model_No " + " Company_Name" + "  Price" + " Manufacuring_Date");
				do {

					System.out.println("  " + rs.getString(2) + "\t\t" + rs.getString(3)+ "\t" +  rs.getInt(4) + "\t" +  rs.getString(5));	
				}while(rs.next());
			}else {
				System.out.println("Oops...! Mobile Not Found.");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		CreateTableMobile();
		int choice;
		
		do {
			System.out.println("1) Add Mobile");
			System.out.println("2) Update Mobile");
			System.out.println("3) Delete Mobile");
			System.out.println("4) View Mobile");
			System.out.println("5) Search Mobile Using Model Number");
			System.out.println("0) Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addMobile(sc);
				break;
			case 2:
				updateMobile(sc);
				break;
			case 3:
				deleteMobile(sc);
				break;
			case 4:
				viewMobiles();
				break;
			case 5:
				searchMobile(sc);
			case 0:
				System.out.println("Thanks For Using Our Services...!");
				break;
			default:
				System.out.println("Invalid Selection,Try Again...!");
			}
		}while(choice != 0);

	}

}
