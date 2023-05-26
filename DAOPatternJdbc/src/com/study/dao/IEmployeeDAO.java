package com.study.dao;

import java.util.List;

import com.study.dto.Employee;
import com.study.exception.NoRecordFoundException;
import com.study.exception.SomethingWentWrongException;

public interface IEmployeeDAO {
	void addEmployee(Employee employee) throws SomethingWentWrongException;
	List<Employee> getEmployeeList() throws SomethingWentWrongException , NoRecordFoundException;
	void updateEmployee(Employee employee) throws SomethingWentWrongException;
	void deleteEmployee(String empId) throws SomethingWentWrongException;
}
