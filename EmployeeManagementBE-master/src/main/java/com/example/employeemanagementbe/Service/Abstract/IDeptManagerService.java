package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import com.example.employeemanagementbe.Model.dept_manager.DeptManagerDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public interface IDeptManagerService {

    Collection<DeptManager> getDeptManagers();
    DeptManager addDeptManager(DeptManagerDTO deptManagerDTO);
    Boolean deleteDeptManager(Long deptManagerID);
    Boolean updateDeptManager(DeptManagerDTO deptManagerDTO,Long id);
    Optional<DeptManager> findDeptManager(Long id);
}
