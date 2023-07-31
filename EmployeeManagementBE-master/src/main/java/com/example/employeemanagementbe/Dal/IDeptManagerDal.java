package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeptManagerDal extends JpaRepository<DeptManager,Long> {

}
