package com.cg.leavemanagement.exception;

/**
 * @author anusha
 * This class provides the response exception of  employee leave details entity
 * 
 */
public class EmployeeIdExceptionResponse {
	
	private String empId;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public EmployeeIdExceptionResponse(String empId) {
		super();
		this.empId = empId;
	}
}

	

