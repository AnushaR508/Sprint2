  package com.cg.leavemanagement;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;
import com.cg.leavemanagement.model.HolidaysEntity;
import com.cg.leavemanagement.service.EmployeeLeaveDetailsService;
import com.cg.leavemanagement.service.MapErrorValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

	@ExtendWith(SpringExtension.class)
	@WebMvcTest
	public class EmployeeLeaveTest {

		@Autowired
		 private MockMvc mockMvc;
	
		 
		@MockBean
		private EmployeeLeaveDetailsService leaveservice;
		@MockBean
		private MapErrorValidation validservice;
		private static ObjectMapper mapper = new ObjectMapper();
		
		@Test
	    public void testPostExample() throws Exception {
	       
	        EmployeeLeaveDetailsEntity emp = new EmployeeLeaveDetailsEntity(10000,"Chamundeshwari",24);
	        Mockito.when(leaveservice.addEmployee(ArgumentMatchers.any())).thenReturn(emp);
	        String json = mapper.writeValueAsString(emp);
	        mockMvc.perform(post("/leave/insert").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
	                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
	                .andExpect(jsonPath("$.empId", Matchers.equalTo(10000)))
	                .andExpect(jsonPath("$.empName", Matchers.equalTo("Chamundeshwari")))
	                .andExpect(jsonPath("$.leaveAvailable", Matchers.equalTo(24)));
	                
	    }
		
		@Test
		public void testPutExample_apply() throws Exception {
			
			EmployeeLeaveDetailsEntity employee = new EmployeeLeaveDetailsEntity();
			LocalDate sdate=LocalDate.of(2020,12,22);
			LocalDate edate=LocalDate.of(2020,12,23);
			employee.setEmpId(10000);
			employee.setLeaveId(1);
			employee.setLeaveType("sick");
			employee.setStartDate(sdate);
			employee.setEndDate(edate);
		    Mockito.when(leaveservice.applyLeave(Mockito.any(EmployeeLeaveDetailsEntity.class))).thenReturn(employee);
	        String json = mapper.writeValueAsString(new EmployeeLeaveDetailsEntity(10000, 1,"sick"));
			mockMvc.perform( MockMvcRequestBuilders
		    .put("/leave/applyleave/1000")
		    .content(json)
		    .contentType(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());	      
		}			      
		
		@Test
		public void testPutExample_cancel() throws Exception {
			
			EmployeeLeaveDetailsEntity employee = new EmployeeLeaveDetailsEntity();
			LocalDate sdate=LocalDate.of(2020,12,22);
			LocalDate edate=LocalDate.of(2020,12,23);
			employee.setEmpId(10000);
			employee.setLeaveId(1);
			employee.setLeaveType("sick");
			employee.setStartDate(sdate);
			employee.setEndDate(edate);
		    Mockito.when(leaveservice.applyLeave(Mockito.any(EmployeeLeaveDetailsEntity.class))).thenReturn(employee);
	        String json = mapper.writeValueAsString(new EmployeeLeaveDetailsEntity(10000, 1,"sick"));
			mockMvc.perform( MockMvcRequestBuilders
		    .put("/leave/cancelleave/1000")
		    .content(json)
		    .contentType(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());	      
		}
		@Test
	    public void testPostHolidays() throws Exception {
	       
	        HolidaysEntity holiday = new HolidaysEntity(1,2020,"01 Jan 2020","New Year");
	        Mockito.when(leaveservice.addHolidays(ArgumentMatchers.any())).thenReturn(holiday);
	        String json = mapper.writeValueAsString(holiday);
	        mockMvc.perform(post("/leave/insertholidaylist").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
	                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
	                .andExpect(jsonPath("$.srNo", Matchers.equalTo(1)))
	                .andExpect(jsonPath("$.year", Matchers.equalTo(2020)))
	                .andExpect(jsonPath("$.date", Matchers.equalTo("01 Jan 2020")))
	                .andExpect(jsonPath("$.description", Matchers.equalTo("New Year")));
	                
	    }
		
	    @Test
	    public void testGetHolidays() throws Exception {
	        List<HolidaysEntity> holiday= new ArrayList<>();
	         HolidaysEntity hol = new  HolidaysEntity(1,2020,"01 Jan 2020","NewYear");
	       holiday.add(hol);
	        Mockito.when(leaveservice.viewHolidayList(2020)).thenReturn(holiday);
	        mockMvc.perform(get("/leave/holidaylist/2020")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
            .andExpect(jsonPath("$[0].year", Matchers.equalTo(2020)));
	
		}	
		
		@Test
	    public void testGetHolidayReport() throws Exception {
         EmployeeLeaveDetailsEntity entity =new EmployeeLeaveDetailsEntity();
         entity.setEmpId(10000);
         entity.setEmpName("baba");
         entity.setLeaveDebit(5);
         entity.setLeaveCredit(3);
         entity.setLeaveAvailable(22);
	        String result="EmployeeLeaveDetailsEntity [EmployeeId=" + entity.getEmpId()+ ", EmployeeName=" + entity.getEmpName() + ", TotalLeaves=24, "
	       		 + "LeaveDebit=" + entity.getLeaveDebit() + ", LeaveCredit=" + entity.getLeaveCredit() +" LeaveBalance = 24"+"-"+"("+entity.getLeaveDebit()+"-"+entity.getLeaveCredit()+")="+entity.getLeaveAvailable()+"]";
	        Mockito.when(leaveservice.viewReport(10000)).thenReturn(result);
	        mockMvc.perform(get("/leave/10000")).andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.equalTo("EmployeeLeaveDetailsEntity [EmployeeId=10000, EmployeeName=baba, TotalLeaves=24, LeaveDebit=5, LeaveCredit=3 LeaveBalance = 24-(5-3)=22]")));
             
	}
	}
		
		
	
