package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeDal extends JpaRepository<Employee,Long> {


}
