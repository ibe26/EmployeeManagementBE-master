package com.example.employeemanagementbe.Service.Concrete;

import com.example.employeemanagementbe.Dal.IDepartmentDal;
import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.department.DepartmentDTO;
import com.example.employeemanagementbe.Service.Abstract.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    public final IDepartmentDal departmentDal;

    public DepartmentService(IDepartmentDal departmentDal) {
        this.departmentDal = departmentDal;
    }

    @Override
    public Collection<Department> getDepartment() {
        return departmentDal.findAll();
    }

    @Override
    public Department addDepartment(DepartmentDTO departmentDTO) {
        Department department=new Department(departmentDTO.getDepartmentName());
            return departmentDal.save(department);
    }

    @Override
    public Boolean deleteDepartment(Long departmentID) {
        if(departmentDal.existsById(departmentID))
        {
            departmentDal.deleteById(departmentID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean updateDepartment(DepartmentDTO departmentDTO, Long id) {
        if(departmentDal.findById(id).isPresent())
        {
            Department department=new Department(id, departmentDTO.getDepartmentName());
            departmentDal.save(department);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Optional<Department> findDepartment(Long id) {
       return departmentDal.findById(id);
    }

}
