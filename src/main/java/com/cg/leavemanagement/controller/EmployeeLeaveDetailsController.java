package com.cg.leavemanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;
import com.cg.leavemanagement.model.HolidaysEntity;
import com.cg.leavemanagement.service.EmployeeLeaveDetailsService;
import com.cg.leavemanagement.service.MapErrorValidation;


/**
 * @author anusha
 * This class provides the controller mappping for post,put and get methods
 * 
 */
@RestController
@RequestMapping("/leave")
public class EmployeeLeaveDetailsController {

	@Autowired
	EmployeeLeaveDetailsService leaveservice;
	
	@Autowired
	private MapErrorValidation mapValidationError;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertEmployee(@Valid @RequestBody EmployeeLeaveDetailsEntity leave, BindingResult result) {
		ResponseEntity<?> errorMap=mapValidationError.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		return new ResponseEntity<EmployeeLeaveDetailsEntity>(leaveservice.addEmployee(leave),HttpStatus.CREATED);
	}
	
	@PutMapping("/applyleave/{employeeId}")
	public ResponseEntity<?> applyEmployeeLeave( @RequestBody EmployeeLeaveDetailsEntity leave,@PathVariable long employeeId){
		return new ResponseEntity<EmployeeLeaveDetailsEntity>(leaveservice.applyLeave(leave),HttpStatus.OK);
	}
	
	@PutMapping("/cancelleave/{employeeId}")
	public ResponseEntity<?> cancelEmployeeLeave(@RequestBody EmployeeLeaveDetailsEntity leave, @PathVariable long employeeId){
		return new ResponseEntity<EmployeeLeaveDetailsEntity>(leaveservice.cancelLeave(leave),HttpStatus.OK);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getLeaveReport(@PathVariable long employeeId){
		 return new ResponseEntity<String>(leaveservice.viewReport(employeeId),HttpStatus.OK);
	}
	
	@PostMapping("/insertholidaylist")
	public ResponseEntity<?> insertHolidayList(@RequestBody HolidaysEntity holidaylist){
		return new ResponseEntity<HolidaysEntity>(leaveservice.addHolidays(holidaylist),HttpStatus.CREATED);
	}
	@GetMapping("/holidaylist/{year}")
	public ResponseEntity<?> getHolidaysList(@PathVariable int year){
		return new ResponseEntity<List<HolidaysEntity>>(leaveservice.viewHolidayList(year),HttpStatus.OK);
	}


}
