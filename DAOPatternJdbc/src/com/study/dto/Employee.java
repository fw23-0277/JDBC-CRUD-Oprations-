package com.study.dto;

import java.time.LocalDate;

public class Employee {
	private String empId;
	private String empName;
	private double annualSalary;
	private LocalDate joiningDate;
	
	public Employee() {
		super();
	}


	public Employee(String empId, String empName, double annualSalary, LocalDate joiningDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.annualSalary = annualSalary;
		this.joiningDate = joiningDate;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public double getAnnualSalary() {
		return annualSalary;
	}


	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}


	public LocalDate getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", annualSalary=" + annualSalary + ", joiningDate="
				+ joiningDate + "]";
	}
	
	
	
	
	
}
