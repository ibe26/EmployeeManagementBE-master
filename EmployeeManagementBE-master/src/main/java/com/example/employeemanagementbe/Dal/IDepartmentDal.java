package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentDal extends JpaRepository<Department,Long> {
}
