package com.cg.leavemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author anusha
 * This class handles user defined exception by extending runtime exception
 * 
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeIdException extends RuntimeException{
	
	private static final long serialVersionID=1L;
	public  EmployeeIdException()
	{
		super();
	}
	public  EmployeeIdException(String errMSg)
	{
		super(errMSg);
	}
}
