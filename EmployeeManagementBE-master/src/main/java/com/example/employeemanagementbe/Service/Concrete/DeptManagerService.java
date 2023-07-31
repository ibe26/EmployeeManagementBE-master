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
    private final IDeptManagerDal _deptManagerDal;
    private final IDepartmentDal _departmentDal;

    @Autowired
    public DeptManagerService(IDeptManagerDal deptManagerDal, IDepartmentDal departmentDal) {
        _deptManagerDal = deptManagerDal;
        _departmentDal = departmentDal;
    }


    @Override
    public Collection<DeptManager> GetDeptManagers() {
        return _deptManagerDal.findAll();
    }

    @Override
    public DeptManager AddDeptManager(DeptManagerDTO deptManagerDTO) {
        Optional<Department> foundDepartment=_departmentDal.findById(deptManagerDTO.getDepartmentID());
        if(foundDepartment.isEmpty()){
            throw new IllegalStateException("No department found.");
        }
        DeptManager deptManager=new DeptManager(deptManagerDTO.getFirstName(), deptManagerDTO.getLastName(), deptManagerDTO.getEmail(),foundDepartment.get());
        return _deptManagerDal.save(deptManager);
    }

    @Override
    public Boolean DeleteDeptManager(Long deptManagerID) {
        if(_deptManagerDal.existsById(deptManagerID))
        {
            _deptManagerDal.deleteById(deptManagerID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean UpdateDeptManager(DeptManagerDTO deptManagerDTO, Long id) {
        if(_deptManagerDal.existsById(id))
        {
            Optional<Department> department=_departmentDal.findById(deptManagerDTO.getDepartmentID());
            if(department.isPresent()){
                DeptManager existingDeptManager=new DeptManager(id, deptManagerDTO.getFirstName(), deptManagerDTO.getLastName(), deptManagerDTO.getEmail(), department.get());
                _deptManagerDal.save(existingDeptManager);
                return Boolean.TRUE;
            }
           }
        return Boolean.FALSE;
    }

    @Override
    public Optional<DeptManager> FindDeptManager(Long id) {
        Optional<DeptManager> deptManagerOptional= _deptManagerDal.findById(id);
        if(deptManagerOptional.isPresent()){
            return deptManagerOptional;
        }
        else return null;
    }

    @Override
    public Optional<DeptManager> FilterDeptManager(Predicate<DeptManager> predicate) {
        Optional<DeptManager> deptManagerOptional= _deptManagerDal.findAll().stream().filter(predicate).findFirst();
        if(deptManagerOptional.isPresent()){
            return deptManagerOptional;
        }
        else return null;
    }
}
