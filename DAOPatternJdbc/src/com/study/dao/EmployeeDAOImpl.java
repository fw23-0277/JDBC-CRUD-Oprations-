package com.study.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.dto.Employee;
import com.study.exception.NoRecordFoundException;
import com.study.exception.SomethingWentWrongException;
import com.study.utility.DBUtils;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void addEmployee(Employee employee) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.createConnection()){
			String insertQuery = "INSERT INTO EMPLOYEE VALUES (?,?,?,?)";
			
			PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
			
			prepareStatement.setString(1, employee.getEmpId());
			prepareStatement.setString(2, employee.getEmpName());
			prepareStatement.setDouble(3, employee.getAnnualSalary());
			prepareStatement.setDate(4, Date.valueOf(employee.getJoiningDate()));
			
			prepareStatement.executeUpdate();
			
		
		} catch (SQLException e) {
			throw new SomethingWentWrongException("Unable to add Employee...!");
		}
		
	
	}

	@Override
	public void updateEmployee(Employee employee) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		try(Connection connection  = DBUtils.createConnection()){
			String updateQuery = "UPDATE EMPLOYEE SET empName = ? , annualSalary = ? , joiningDate = ? WHERE  empId = ?";
			
			PreparedStatement prepareStatement = connection.prepareStatement(updateQuery);
			
			prepareStatement.setString(1, employee.getEmpName());
			prepareStatement.setDouble(2,employee.getAnnualSalary());
			prepareStatement.setDate(3,Date.valueOf(employee.getJoiningDate()) );
			prepareStatement.setString(4, employee.getEmpId());
			
			prepareStatement.executeUpdate();
			
		}catch(SQLException e) {
			throw new SomethingWentWrongException("Unable to update Employeee...!");
		}
		
	}

	@Override
	public void deleteEmployee(String  empId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.createConnection()){
			
			String deleteEmployee = "DELETE FROM EMPLOYEE WHERE empId = ?";
			
			PreparedStatement prepareStatement = connection.prepareStatement(deleteEmployee);
			
			prepareStatement.setString(1, empId);
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new SomethingWentWrongException(empId);
		}
		
	}

	@Override
	public List<Employee> getEmployeeList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		List<Employee> employeeList = new ArrayList<>();
		
		try(Connection connection = DBUtils.createConnection()){
			
			String selectQuery = "SELECT * FROM EMPLOYEE";
			
			PreparedStatement prepareStatement = connection.prepareStatement(selectQuery);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No Employee Found");
			}
			while(rs.next()) {
				Employee emp = new Employee(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getDate(4).toLocalDate());
				employeeList.add(emp);
			}
				
		}catch (SQLException e) {
			throw new SomethingWentWrongException("Unable to get Employee");
		}
		return employeeList;
	}

}
