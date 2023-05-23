package com.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmolpyeeCrudOprations {

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
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return connection;
	}

	static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Database Connection Closed...!");
			}
		}
	}

	static void CreateTables() {
		Connection connection = createConnection();
		String createTableQuery = "CREATE TABLE IF NOT EXISTS EMPLOYEE ( empId VARCHAR(5) PRIMARY KEY,empName VARCHAR(20) NOT NULL , annualSalary DOUBLE NOT NULL, joiningDate DATE );";

		try {
			Statement statement = connection.createStatement();
			statement.execute(createTableQuery);
		} catch (SQLException e) {
			System.out.println("Oops...! Table is Not Created...!");
		} finally {
			closeConnection(connection);
		}
	}

	static void insertEmployee(Scanner sc) {
		System.out.print("Enter Employee ID : ");
		String empId = sc.next();
		System.out.print("Enter Employee Name : ");
		String empName = sc.next();
		System.out.print("Enter Employee Annual Salary : ");
		double empSalary = sc.nextDouble();
		System.out.print("Enter Joining Date [ YYYY-MM-DD ] : ");
		String joingDate = sc.next();

		String insertQuery = "INSERT INTO EMPLOYEE VALUES(?,?,?,?)";

		Connection connection = createConnection();

		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(insertQuery);

			prepareStatement.setString(1, empId);
			prepareStatement.setString(2, empName);
			prepareStatement.setDouble(3, empSalary);
			prepareStatement.setString(4, joingDate);

			if (prepareStatement.executeUpdate() > 0) {
				System.out.println("Employee Added Succesfully...!");
			} else {
				System.out.println("Unable Add to Employee into Database...!");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

	}
	
	static void viewEmployee() {
		Connection connection = createConnection();
		
		String selectQuery = "SELECT * FROM EMPLOYEE";
		
		 try {
			PreparedStatement prepareStatement =  connection.prepareStatement(selectQuery);
			
			ResultSet rs  = prepareStatement.executeQuery();
			
			
			
			if(rs.next()) {
				System.out.println("EmployeeId " + " EmployeeName" + " EmployeeAnnualSalary" + " EmployeeJoiningDate");
				do {
					
					System.out.println("   " + rs.getString(1) + "\t\t" + rs.getString(2)+ "\t\t" +  rs.getDouble(3) + "\t\t" +  rs.getString(4));	
				}while(rs.next());
			}else {
				System.out.println("Oops...! Employee Not Found.");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnection(connection);
		}
	}

	static void updateEmployee(Scanner sc) {
		System.out.print("Enter Employee ID : ");
		String empId = sc.next();
		System.out.print("Enter Employee Name : ");
		String empName = sc.next();
		System.out.print("Enter Employee Annual Salary : ");
		double empSalary = sc.nextDouble();
		System.out.print("Enter Joining Date [ YYYY-MM-DD ] : ");
		String joingDate = sc.next();

		String updateQuery = "UPDATE Employee SET empName = ? , annualSalary = ? , joiningDate = ? where empId = ?";

		Connection connection = createConnection();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(updateQuery);
			prepareStatement.setString(1, empName);
			prepareStatement.setDouble(2, empSalary);
			prepareStatement.setString(3, joingDate);
			prepareStatement.setString(4, empId);

			if (prepareStatement.executeUpdate() > 0) {
				System.out.println("Employee Update Successfully...!");
			} else {
				System.out.println("Employee Not Updated...!");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(connection);
		}

	}
	
	static void deleteEmployee(Scanner sc ) {
		System.out.print("Enter Employee ID : ");
		String empId = sc.next();
		
		
		String deleteQuery = "DELETE FROM EMPLOYEE WHERE empId = ?";
		
		Connection connection = createConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(deleteQuery);
			
			prepareStatement.setString(1, empId);
			
			if(prepareStatement.executeUpdate() > 0) {
				System.out.println("Employee Deleted Succesfully...!");
			}else {
				System.out.println("Oops...! Employee Not Exist.");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnection(connection);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		CreateTables();

		int choice;

		do {
			System.out.println();
			System.out.println("1) Add Employee");
			System.out.println("2) View Employee");
			System.out.println("3) Update Employee");
			System.out.println("4) Delete Employee");
			System.out.println("0) Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				insertEmployee(sc);
				break;
			case 2:
				viewEmployee();
				break;
			case 3:
				updateEmployee(sc);
				break;
			case 4:
				deleteEmployee(sc);
				break;
			case 0:
				System.out.println("Thanks For Using Our Services...!");
				break;
			default:
				System.out.println("Invalid Selection,Try Again...!");
			}

		} while (choice != 0);

	}

}
