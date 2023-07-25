package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.employee.Employee;
import com.example.employeemanagementbe.Model.employee.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface IEmployeeService {
     Collection<Employee> GetEmployees();
     Employee AddEmployee(EmployeeDTO employeeDTO);
     Boolean DeleteEmployee(Long empID);
     Boolean UpdateEmployee(EmployeeDTO employeeDTO,Long id);
     Optional<Employee> FindEmployee(Long id);
}
