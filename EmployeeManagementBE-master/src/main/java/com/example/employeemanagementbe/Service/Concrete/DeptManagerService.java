package com.example.employeemanagementbe.Service.Concrete;

import com.example.employeemanagementbe.Dal.IDepartmentDal;
import com.example.employeemanagementbe.Dal.IDeptManagerDal;
import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import com.example.employeemanagementbe.Model.dept_manager.DeptManagerDTO;
import com.example.employeemanagementbe.Service.Abstract.IDeptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class DeptManagerService implements IDeptManagerService {
    private final IDeptManagerDal deptManagerDal;
    private final IDepartmentDal departmentDal;

    @Autowired
    public DeptManagerService(IDeptManagerDal deptManagerDal, IDepartmentDal departmentDal) {
        this.deptManagerDal = deptManagerDal;
        this.departmentDal = departmentDal;
    }


    @Override
    public Collection<DeptManager> getDeptManagers() {
        return deptManagerDal.findAll();
    }

    @Override
    public DeptManager addDeptManager(DeptManagerDTO deptManagerDTO) {
        Optional<Department> foundDepartment=departmentDal.findById(deptManagerDTO.getDepartmentID());
        if(foundDepartment.isEmpty()){
            throw new IllegalStateException("No department found.");
        }
        DeptManager deptManager=new DeptManager(deptManagerDTO.getFirstName(), deptManagerDTO.getLastName(), deptManagerDTO.getEmail(),foundDepartment.get());
        return deptManagerDal.save(deptManager);
    }

    @Override
    public Boolean deleteDeptManager(Long deptManagerID) {
        if(deptManagerDal.existsById(deptManagerID))
        {
            deptManagerDal.deleteById(deptManagerID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean updateDeptManager(DeptManagerDTO deptManagerDTO, Long id) {
        if(deptManagerDal.existsById(id))
        {
            Optional<Department> department=departmentDal.findById(deptManagerDTO.getDepartmentID());
            if(department.isPresent()){
                DeptManager existingDeptManager=new DeptManager(id, deptManagerDTO.getFirstName(), deptManagerDTO.getLastName(), deptManagerDTO.getEmail(), department.get());
                deptManagerDal.save(existingDeptManager);
                return Boolean.TRUE;
            }
           }
        return Boolean.FALSE;
    }

    @Override
    public Optional<DeptManager> findDeptManager(Long id) {
        Optional<DeptManager> deptManagerOptional= deptManagerDal.findById(id);
        if(deptManagerOptional.isPresent()){
            return deptManagerOptional;
        }
        else return null;
    }
}
