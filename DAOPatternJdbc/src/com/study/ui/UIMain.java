package com.study.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.study.dto.Employee;
import com.study.exception.NoRecordFoundException;
import com.study.exception.SomethingWentWrongException;
import com.study.service.EmployeeServiceImpl;
import com.study.service.IEmployeeService;

public class UIMain {
	static void insertEmployee(Scanner sc) {
		System.out.print("Enter Employee ID : ");
		String empId = sc.next();
		System.out.print("Enter Employee Name : ");
		String empName = sc.next();
		System.out.print("Enter Employee Annual Salary : ");
		double annualSalary = sc.nextDouble();
		System.out.print("Enter Joining Date [ YYYY-MM-DD ] : ");
		LocalDate joiningDate = LocalDate.parse(sc.next());

		
		Employee emp = new Employee(empId,empName,annualSalary,joiningDate);
		
		IEmployeeService employeeServices = new EmployeeServiceImpl();
		
		try {
			employeeServices.addEmployee(emp);
			System.out.println("Employee Added Sucessfully...!");
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void viewEmployees(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		IEmployeeService employeeService = new EmployeeServiceImpl();
		
		try {
			List<Employee> employeeList = employeeService.getEmoloyeeList();
			
			int choice ;
			System.out.println("1. See in the order of EmpId DESC");
			System.out.println("2. See in the order of EmpName ASC");
			System.out.println("3. See in the order of joiningDate ASC");
			System.out.print("Please enter selection ");
			choice = sc.nextInt();
			switch(choice) {
			case 1 -> employeeService.getEmployeesListByEmpIdDESC(employeeList).stream().forEach(System.out::println);
			case 2 -> employeeService.getEmployeeListByEmpNameASC(employeeList).stream().forEach(System.out::println);
			case 3 -> employeeService.getEmployeeListByJoiningDateASC(employeeList).stream().forEach(System.out::println);
			default -> employeeList.stream().forEach(System.out::println);
			}
			
		} catch (SomethingWentWrongException  e) {
			// TODO: handle exception
			throw new SomethingWentWrongException("Unable to fetch Employee...!");
			
		}catch (NoRecordFoundException e) {
			throw new NoRecordFoundException("Record Not Found...!"); 
		}
		
	}
	
	static void updateEmployee(Scanner sc) {
		System.out.print("Enter Employee ID : ");
		String empId = sc.next();
		System.out.print("Enter Employee Name : ");
		String empName = sc.next();
		System.out.print("Enter Employee Annual Salary : ");
		double annualSalary = sc.nextDouble();
		System.out.print("Enter Joining Date [ YYYY-MM-DD ] : ");
		LocalDate joiningDate = LocalDate.parse(sc.next());
		
		Employee employee = new Employee(empId,empName,annualSalary,joiningDate);
		
		IEmployeeService employeeService = new EmployeeServiceImpl();
		
		try {
			employeeService.updateEmployee(employee);
			System.out.println("Employee Update Sucessfully...!");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
			
		}

	}
	
	static void deleteEmployee(Scanner sc) {
		System.out.print("Enter Employee Id :");
		String empId = sc.next();
		
		IEmployeeService employeeService = new EmployeeServiceImpl();
		
		
		try {
			employeeService.deleteEmployee(empId);
			System.out.println("Employee Deleted Succesfully...!");
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	

	public static void main(String[] args) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int choice;

		do {
			System.out.println("1. Insert Employee");
			System.out.println("2. View Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					insertEmployee(sc);
					break;
				case 2:
					viewEmployees(sc);
					break;
				case 3:
					updateEmployee(sc);
					break;
				case 4:
					deleteEmployee(sc);
					break;
				case 0:
					System.out.println("Thanks for using our services");
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}

		} while (choice != 0);

	}

}
