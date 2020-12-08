package com.cg.leavemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;
import com.cg.leavemanagement.model.HolidaysEntity;
import com.cg.leavemanagement.repository.EmployeeLeaveDetailsRepository;
import com.cg.leavemanagement.repository.HolidaysDetails;
import com.cg.leavemanagement.service.EmployeeLeaveDetailsServiceImpl;

public class EmployeeServiceTest {
	
@Mock
EmployeeLeaveDetailsRepository employeeRepository;

@Mock
HolidaysDetails holidaydetails;

@InjectMocks
EmployeeLeaveDetailsServiceImpl employeeServiceImpl;

@BeforeEach
public void setup() {
	MockitoAnnotations.initMocks(this);
}

@Test
 void test_addEmployee() throws Exception {

	 EmployeeLeaveDetailsEntity emp = new EmployeeLeaveDetailsEntity(1000,"baba",24);
     when(employeeRepository.save(emp)).thenReturn(emp);
    EmployeeLeaveDetailsEntity employee= employeeServiceImpl.addEmployee(emp);
    assertNotNull(employee);
}

@Test
void test_applyLeave() throws Exception {

	 EmployeeLeaveDetailsEntity emp = new EmployeeLeaveDetailsEntity();
	 LocalDate startdate=LocalDate.of(2020, 12, 23);
	 LocalDate enddate=LocalDate.of(2020, 12, 25);
	 emp.setEmpId(1000);
	 emp.setEmpName("baba");
	 emp.setLeaveId(1);
	 emp.setLeaveType("sick");
	 emp.setStartDate(startdate);
	 emp.setEndDate(enddate);
	 emp.setLeaveApplied(1);
	 when(employeeRepository.save(emp)).thenReturn(emp);
	 when(employeeRepository.findByEmpId(1000)).thenReturn(emp);
	  EmployeeLeaveDetailsEntity employee= employeeServiceImpl.applyLeave(emp);
     assertEquals(1000, employee.getEmpId());
     assertEquals(1, employee.getLeaveId());
     assertEquals("sick",employee.getLeaveType());
     assertEquals(startdate,employee.getStartDate());
     assertEquals(enddate,employee.getEndDate());
     assertEquals(1,employee.getLeaveApplied());
}

@Test
void test_cancelLeave() throws Exception {

	 EmployeeLeaveDetailsEntity emp = new EmployeeLeaveDetailsEntity();
	 LocalDate startdate=LocalDate.of(2020, 12, 23);
	 LocalDate enddate=LocalDate.of(2020, 12, 25);
	 emp.setEmpId(1000);
	 emp.setEmpName("baba");
	 emp.setLeaveId(1);
	 emp.setLeaveType("sick");
	 emp.setStartDate(startdate);
	 emp.setEndDate(enddate);
	 emp.setLeaveCancelled(1);
	 when(employeeRepository.save(emp)).thenReturn(emp);
	 when(employeeRepository.findByEmpId(1000)).thenReturn(emp);
	  EmployeeLeaveDetailsEntity employee= employeeServiceImpl.cancelLeave(emp);
     assertEquals(1, employee.getLeaveId());
     assertEquals("sick",employee.getLeaveType());
     assertEquals(startdate,employee.getStartDate());
     assertEquals(enddate,employee.getEndDate());
     assertEquals(1,employee.getLeaveCancelled());
}

@Test
void test_viewReport() throws Exception {
	
	EmployeeLeaveDetailsEntity entity = new EmployeeLeaveDetailsEntity();
	entity.setEmpId(1000);
    entity.setEmpName("baba");
    entity.setLeaveDebit(5);
    entity.setLeaveCredit(3);
    entity.setLeaveAvailable(22);
    when(employeeRepository.save(entity)).thenReturn(entity );
	when(employeeRepository.findByEmpId(1000)).thenReturn(entity);
    String result=employeeServiceImpl.viewReport(entity.getEmpId());
    String result1 ="EmployeeLeaveDetailsEntity [EmployeeId=" + entity.getEmpId()+ ", EmployeeName=" + entity.getEmpName() + ", TotalLeaves=24, "
      		 + ", LeaveDebit=" + entity.getLeaveDebit() + ", LeaveCredit=" + entity.getLeaveCredit() +"\n"+ " LeaveBalance = 24"+"-"+"("+entity.getLeaveDebit()+"-"+entity.getLeaveCredit()+")="+entity.getLeaveAvailable()+"]";
    assertNotNull(result);
    assertEquals(result, result1);
}

@Test
void test_addHolidays() throws Exception {
	
	HolidaysEntity holiday= new HolidaysEntity(1,2020, "01 Jan 2020","NewYear");
	when(holidaydetails.save(holiday)).thenReturn(holiday);
	when(employeeServiceImpl.addHolidays(holiday)).thenReturn(holiday);
	    assertNotNull(holiday);
	    assertEquals(1,holiday.getSrNo());
	    assertEquals(2020,holiday.getYear());
	    assertEquals("01 Jan 2020",holiday.getDate());
	    assertEquals("NewYear",holiday.getDescription());	    
}
   
@Test
void test_viewHolidayList() throws Exception {
	List<HolidaysEntity> holiday= new ArrayList<>();
    HolidaysEntity hol = new  HolidaysEntity(1,2020,"01 Jan 2020","NewYear");
    holiday.add(hol);
    assertNotNull(holiday);
    assertEquals(1,hol.getSrNo());
    assertEquals(2020,hol.getYear());
    assertEquals("01 Jan 2020",hol.getDate());
    assertEquals("NewYear",hol.getDescription());	    
}
    
}

    
    
