package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.department.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface IDepartmentService {
    Collection<Department> GetDepartment();
    Department AddDepartment(DepartmentDTO departmentDTO);
    Boolean DeleteDepartment(Long departmentID);
    Boolean UpdateDepartment(DepartmentDTO departmentDTO, Long id);
    Optional<Department> FindDepartment(Long id);
}
