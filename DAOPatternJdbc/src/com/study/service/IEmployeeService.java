package com.study.service;

import java.util.List;

import com.study.dto.Employee;
import com.study.exception.NoRecordFoundException;
import com.study.exception.SomethingWentWrongException;

public interface IEmployeeService {
	void addEmployee(Employee employee) throws SomethingWentWrongException;
	List<Employee> getEmoloyeeList() throws SomethingWentWrongException , NoRecordFoundException;
 	void updateEmployee(Employee employee) throws SomethingWentWrongException;
	void deleteEmployee(String empId) throws SomethingWentWrongException;
	
	List<Employee> getEmployeesListByEmpIdDESC(List<Employee> employeeList);
	List<Employee> getEmployeeListByEmpNameASC(List<Employee> employeeList);
	List<Employee> getEmployeeListByJoiningDateASC(List<Employee> employeeList);
	
}
