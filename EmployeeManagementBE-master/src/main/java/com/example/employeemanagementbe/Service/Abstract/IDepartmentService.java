package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.department.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface IDepartmentService {
    Collection<Department> getDepartment();
    Department addDepartment(DepartmentDTO departmentDTO);
    Boolean deleteDepartment(Long departmentID);
    Boolean updateDepartment(DepartmentDTO departmentDTO, Long id);
    Optional<Department> findDepartment(Long id);
}
