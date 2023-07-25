package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IEmployeeDal extends JpaRepository<Employee,Long> {


}
