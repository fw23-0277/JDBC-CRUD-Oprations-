package com.study.service;

import java.util.List;

import com.study.dao.EmployeeDAOImpl;
import com.study.dao.IEmployeeDAO;
import com.study.dto.Employee;
import com.study.exception.NoRecordFoundException;
import com.study.exception.SomethingWentWrongException;

public class EmployeeServiceImpl implements IEmployeeService {

	@Override
	public void addEmployee(Employee employee) throws SomethingWentWrongException {
		IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
		employeeDAO.addEmployee(employee);

	}

	@Override
	public void updateEmployee(Employee employee) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
		employeeDAO.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(String empId) throws SomethingWentWrongException {
		IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
		employeeDAO.deleteEmployee(empId);
	}

	@Override
	public List<Employee> getEmoloyeeList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
		return employeeDAO.getEmployeeList();
	}

	@Override
	public List<Employee> getEmployeesListByEmpIdDESC(List<Employee> employeeList) {
		// TODO Auto-generated method stub
		return employeeList.stream().sorted((e1,e2)->e2.getEmpId().compareTo(e1.getEmpId())).toList();
	}

	@Override
	public List<Employee> getEmployeeListByEmpNameASC(List<Employee> employeeList) {
		// TODO Auto-generated method stub
		return employeeList.stream().sorted((e1,e2)->e1.getEmpId().compareTo(e2.getEmpId())).toList();
	}

	@Override
	public List<Employee> getEmployeeListByJoiningDateASC(List<Employee> employeeList) {
		// TODO Auto-generated method stub
		return employeeList.stream().sorted((e1,e2)->e1.getJoiningDate().compareTo(e2.getJoiningDate())).toList();
	}

}
