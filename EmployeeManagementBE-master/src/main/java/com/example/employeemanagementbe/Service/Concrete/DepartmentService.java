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

    public final IDepartmentDal _departmentDal;

    public DepartmentService(IDepartmentDal departmenDal) {
        _departmentDal = departmenDal;
    }

    @Override
    public Collection<Department> GetDepartment() {
        return _departmentDal.findAll();
    }

    @Override
    public Department AddDepartment(DepartmentDTO departmentDTO) {
        Department department=new Department(departmentDTO.getDepartmentName());
            return _departmentDal.save(department);
    }

    @Override
    public Boolean DeleteDepartment(Long departmentID) {
        if(_departmentDal.existsById(departmentID))
        {
            _departmentDal.deleteById(departmentID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean UpdateDepartment(DepartmentDTO departmentDTO, Long id) {
        if(_departmentDal.findById(id).isPresent())
        {
            Department department=new Department(id, departmentDTO.getDepartmentName());
            _departmentDal.save(department);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Optional<Department> FindDepartment(Long id) {
       return _departmentDal.findById(id);
    }

}
