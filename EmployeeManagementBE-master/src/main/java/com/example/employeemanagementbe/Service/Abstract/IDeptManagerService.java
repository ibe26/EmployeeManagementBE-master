package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import com.example.employeemanagementbe.Model.dept_manager.DeptManagerDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public interface IDeptManagerService {

    Collection<DeptManager> GetDeptManagers();
    DeptManager AddDeptManager(DeptManagerDTO deptManagerDTO);
    Boolean DeleteDeptManager(Long deptManagerID);
    Boolean UpdateDeptManager(DeptManagerDTO deptManagerDTO,Long id);
    Optional<DeptManager> FindDeptManager(Long id);
    Optional<DeptManager> FilterDeptManager(Predicate<DeptManager> predicate);
}
