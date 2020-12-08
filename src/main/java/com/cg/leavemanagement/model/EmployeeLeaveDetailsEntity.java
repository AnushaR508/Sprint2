package com.cg.leavemanagement.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class EmployeeLeaveDetailsEntity {
	    /**
	     *  Employee Id which is referred as primary key
	     */
	    @Id
	    private long empId;
	   
		/**
	     *  Employee Name
	     */
	    @NotBlank(message = "EmployeeName is required")
		private String empName;
		 /**
	     *  leave Id with reference to leave type
	     */
		private int leaveId;
		 /**
	     *  Employee Leave Type
	     */
		private String leaveType;
		 /**
	     *  Start Date of the leave
	     */
		@JsonFormat(pattern="yyyy-MM-dd")
		private LocalDate startDate;
		 /**
	     *  End Date of the leave
	     */
		@JsonFormat(pattern="yyyy-MM-dd")
		private LocalDate endDate;
		 /**
	     *  Total available leaves for an employee
	     */
		private int leaveAvailable;
		 /**
	     * Total leaves approved for an employee
	     */
		private int leaveDebit;
		 /**
	     *  Total leaves cancelled by an employee
	     */
		private int leaveCredit;
		/**
	     *  Leave Applied status
	     */
		private int leaveApplied;
		/**
	     *  Leave Cancelled status
	     */
		private int leaveCancelled;
		public int getLeaveCredit() {
			return leaveCredit;
		}
		public long getEmpId() {
			return empId;
		}
		public void setEmpId(long empId) {
			this.empId = empId;
		}
		public String getEmpName() {
			return empName;
		}
		public EmployeeLeaveDetailsEntity(long empId, int leaveId, String leaveType) {
			super();
			this.empId = empId;
			this.leaveId = leaveId;
			this.leaveType = leaveType;
		}
		public EmployeeLeaveDetailsEntity(long empId, String empName, int leaveAvailable) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.leaveAvailable = leaveAvailable;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public void setLeaveCredit(int leaveCredit) {
			this.leaveCredit = leaveCredit;
		}
		public int getLeaveId() {
			return leaveId;
		}
		public void setLeaveId(int leaveId) {
			this.leaveId = leaveId;
		}
		public String getLeaveType() {
			return leaveType;
		}
		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public int getLeaveAvailable() {
			return leaveAvailable;
		}
		public void setLeaveAvailable(int leaveAvailable) {
			this.leaveAvailable = leaveAvailable;
		}
		public int getLeaveDebit() {
			return leaveDebit;
		}
		public void setLeaveDebit(int leaveDebit) {
			this.leaveDebit = leaveDebit;
		}
		public int getLeaveApplied() {
			return leaveApplied;
		}
		public void setLeaveApplied(int leaveApplied) {
			this.leaveApplied = leaveApplied;
		}
		public int getLeaveCancelled() {
			return leaveCancelled;
		}
		public void setLeaveCancelled(int leaveCancelled) {
			this.leaveCancelled = leaveCancelled;
		}
		public EmployeeLeaveDetailsEntity(long empId,  String empName,
				 int leaveId,String leaveType, LocalDate startDate, LocalDate endDate,
				int leaveAvailable, int leaveDebit, int leaveCredit, int leaveApplied, int leaveCancelled) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.leaveId = leaveId;
			this.leaveType = leaveType;
			this.startDate = startDate;
			this.endDate = endDate;
			this.leaveAvailable = leaveAvailable;
			this.leaveDebit = leaveDebit;
			this.leaveCredit = leaveCredit;
			this.leaveApplied = leaveApplied;
			this.leaveCancelled = leaveCancelled;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (empId ^ (empId >>> 32));
			result = prime * result + ((empName == null) ? 0 : empName.hashCode());
			result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
			result = prime * result + leaveApplied;
			result = prime * result + leaveAvailable;
			result = prime * result + leaveCancelled;
			result = prime * result + leaveCredit;
			result = prime * result + leaveDebit;
			result = prime * result + leaveId;
			result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
			result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EmployeeLeaveDetailsEntity other = (EmployeeLeaveDetailsEntity) obj;
			if (empId != other.empId)
				return false;
			if (empName == null) {
				if (other.empName != null)
					return false;
			} else if (!empName.equals(other.empName))
				return false;
			if (endDate == null) {
				if (other.endDate != null)
					return false;
			} else if (!endDate.equals(other.endDate))
				return false;
			if (leaveApplied != other.leaveApplied)
				return false;
			if (leaveAvailable != other.leaveAvailable)
				return false;
			if (leaveCancelled != other.leaveCancelled)
				return false;
			if (leaveCredit != other.leaveCredit)
				return false;
			if (leaveDebit != other.leaveDebit)
				return false;
			if (leaveId != other.leaveId)
				return false;
			if (leaveType == null) {
				if (other.leaveType != null)
					return false;
			} else if (!leaveType.equals(other.leaveType))
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			return true;
		}
		public EmployeeLeaveDetailsEntity() {
			super();
		}
		
		
		@Override
		public String toString() {
			return "EmployeeLeaveDetailsEntity [EmployeeId=" + empId + ", EmployeeName=" + empName + ", TotalLeaves=24, "
					 + ", LeaveDebit=" + leaveDebit + ", LeaveCredit=" + leaveCredit +"\n LeaveBalance = 24"+"-"+"("+leaveDebit+"-"+leaveCredit+")="+leaveAvailable+"]";
		}
}