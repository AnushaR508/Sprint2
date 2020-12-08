package com.cg.leavemanagement.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.leavemanagement.exception.EmployeeIdException;
import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;
import com.cg.leavemanagement.model.HolidaysEntity;
import com.cg.leavemanagement.repository.*;

/**
 * @author anusha
 * This class implements emloyee leave details services
 * 
 */
@Service
public class EmployeeLeaveDetailsServiceImpl implements EmployeeLeaveDetailsService{
    @Autowired
	EmployeeLeaveDetailsRepository employeerepository;
    @Autowired
    HolidaysDetails holidays;
    final int totalLeaves=24;

	/**
	 * @author anusha
	 * This method is used to add employee to the database
	 * 
	 */
	@Override
	public EmployeeLeaveDetailsEntity addEmployee(EmployeeLeaveDetailsEntity details) {
		EmployeeLeaveDetailsEntity emp=employeerepository.findByEmpId(details.getEmpId());
		if(emp!=null)
			throw new EmployeeIdException("Employee already exists");
		return  employeerepository.save(details);
		
	}
	
	/**
	 * @author anusha
	 * This method is used to apply leave for an employee
	 * 
	 */
	@Override
	public EmployeeLeaveDetailsEntity applyLeave(EmployeeLeaveDetailsEntity details) {
		EmployeeLeaveDetailsEntity entity= employeerepository.findByEmpId(details.getEmpId());
		details.setEmpId(details.getEmpId());
		LocalDate sdate=details.getStartDate();
		LocalDate edate=details.getEndDate();
		if(!sdate.isBefore(edate))
			throw new EmployeeIdException(details.getEmpId()+" not eligible to apply for a leave");
		{
	
			Period diff=Period.between(details.getStartDate(), details.getEndDate());
			int debit=diff.getDays();
			entity.setLeaveDebit(entity.getLeaveDebit()+debit);
			entity.setLeaveAvailable(totalLeaves-(entity.getLeaveDebit()-entity.getLeaveCredit()));
			entity.setLeaveId(details.getLeaveId());
			entity.setLeaveType(details.getLeaveType());
			entity.setStartDate(details.getStartDate());
			entity.setEndDate(details.getEndDate());
			entity.setLeaveApplied(1);
			return employeerepository.save(entity);
		}
	
	}

	/**
	 * @author anusha
	 * This method is used to cancel leave for an employee
	 * 
	 */
	@Override
	public EmployeeLeaveDetailsEntity cancelLeave(EmployeeLeaveDetailsEntity details) {
		EmployeeLeaveDetailsEntity entity= employeerepository.findByEmpId(details.getEmpId());
		if(entity!=null)
		{
	    entity.setStartDate(details.getStartDate());
		entity.setEndDate(details.getEndDate());
		Period diff=Period.between(details.getStartDate(), details.getEndDate());
		int credit=diff.getDays();
		entity.setLeaveCredit(entity.getLeaveCredit()+credit);
		entity.setLeaveAvailable(totalLeaves-(entity.getLeaveDebit()-entity.getLeaveCredit()));
		entity.setLeaveCancelled(1);
		return employeerepository.save(entity);
		}
		return null;
	}
     

	/**
	 * @author anusha
	 * This method is used to view leave report of an employee
	 * 
	 */
	@Override
	public String viewReport(long empid) {
		EmployeeLeaveDetailsEntity entity= employeerepository.findByEmpId(empid);
		return "EmployeeLeaveDetailsEntity [EmployeeId=" + entity.getEmpId()+ ", EmployeeName=" + entity.getEmpName() + ", TotalLeaves=24, "
		 + ", LeaveDebit=" + entity.getLeaveDebit() + ", LeaveCredit=" + entity.getLeaveCredit() +"\n LeaveBalance = 24"+"-"+"("+entity.getLeaveDebit()+"-"+entity.getLeaveCredit()+")="+entity.getLeaveAvailable()+"]";
	}
	

	/**
	 * @author anusha
	 * This method is used to add holidays of an year
	 * 
	 */
	@Override
	public HolidaysEntity addHolidays(HolidaysEntity holiday) {
		return holidays.save(holiday);
	}

	/**
	 * @author anusha
	 * This method is used to view holiday list of an year
	 * 
	 */

	@Override
	public  List<HolidaysEntity> viewHolidayList(int year) {
		return holidays.findByYear(year);
		
	}
}
