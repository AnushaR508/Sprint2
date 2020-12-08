package com.cg.leavemanagement.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HolidaysEntity {
	    @Id
	    /**
	     * Serial number as primary key
	     */
		private int srNo;
	    /**
	     * Year of the holiday list
	     */
		private int year;
		
		/**
	     * Date of holiday
	     */
		private String date;
		/**
	     * Desciption of holiday
	     */
		private String description;
		public HolidaysEntity(int srNo, int year, String date, String description) {
			super();
			this.srNo = srNo;
			this.year = year;
			this.date = date;
			this.description = description;
		}
		public HolidaysEntity() {
			super();
		}
		
	  
		public int getSrNo() {
			return srNo;
		}
		public void setSrNo(int srNo) {
			this.srNo = srNo;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}


