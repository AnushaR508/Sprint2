package com.cg.leavemanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;
import com.cg.leavemanagement.model.HolidaysEntity;

/**
 * @author anusha
 * This EmployeeLeaveService  provides functionalities as per the requirements
 */
@Service
public interface EmployeeLeaveDetailsService {
	/**
	 * This method will add new employees to the DB using JPA
	 * @param employee details to be added to the database
	 */
	public abstract EmployeeLeaveDetailsEntity  addEmployee(EmployeeLeaveDetailsEntity details);
	/**
	 * This method will allow employee to apply leave
	 * @param employee leave details to be updated in the database
	 */
	public abstract EmployeeLeaveDetailsEntity  applyLeave(EmployeeLeaveDetailsEntity details);
	/**
	 * This method will allow employee to cancel  leave
	 * @param employee leave details to be updated in the database
	 */

	public abstract EmployeeLeaveDetailsEntity cancelLeave(EmployeeLeaveDetailsEntity details);
	/**
	 * This method will allow employee view leave report
	 * @param employee id to be found
	 * @return employee if found else null
	 */

	public abstract String viewReport(long empid);
	/**
	 * This method will allow employee to view holiday list
	 * @param year is to be found
	 */

	public abstract HolidaysEntity addHolidays(HolidaysEntity holiday);
	public abstract List<HolidaysEntity> viewHolidayList(int year);

}