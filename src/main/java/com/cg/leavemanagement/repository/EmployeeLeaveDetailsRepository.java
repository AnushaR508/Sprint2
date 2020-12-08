package com.cg.leavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cg.leavemanagement.model.EmployeeLeaveDetailsEntity;

/**
 * @author anusha
 * This class extends crud repository to manage crud operations performed with database
 * 
 */
@Repository
public interface EmployeeLeaveDetailsRepository extends CrudRepository<EmployeeLeaveDetailsEntity,Long> {
     EmployeeLeaveDetailsEntity findByEmpId(long empId);
     EmployeeLeaveDetailsEntity findByEmpName(String empName);
     
}
