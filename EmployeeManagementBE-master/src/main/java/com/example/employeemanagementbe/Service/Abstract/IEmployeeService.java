package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.employee.Employee;
import com.example.employeemanagementbe.Model.employee.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface IEmployeeService {
     Collection<Employee> getEmployees();
     Employee addEmployee(EmployeeDTO employeeDTO);
     Boolean deleteEmployee(Long empID);
     Boolean updateEmployee(EmployeeDTO employeeDTO,Long id);
     Optional<Employee> findEmployee(Long id);
}
