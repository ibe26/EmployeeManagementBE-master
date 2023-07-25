package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartmentDal extends JpaRepository<Department,Long> {
}
