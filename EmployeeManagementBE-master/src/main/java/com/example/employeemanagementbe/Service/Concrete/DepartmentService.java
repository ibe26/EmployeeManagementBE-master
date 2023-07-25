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

    public final IDepartmentDal _departmenDal;

    public DepartmentService(IDepartmentDal departmenDal) {
        _departmenDal = departmenDal;
    }

    @Override
    public Collection<Department> GetDepartment() {
        return _departmenDal.findAll();
    }

    @Override
    public Department AddDepartment(DepartmentDTO departmentDTO) {
        Department department=new Department(departmentDTO.getDepartmentName());
            return _departmenDal.save(department);
    }

    @Override
    public Boolean DeleteDepartment(Long departmentID) {
        if(_departmenDal.existsById(departmentID))
        {
            _departmenDal.deleteById(departmentID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean UpdateDepartment(DepartmentDTO departmentDTO, Long id) {
        if(_departmenDal.findById(id).isPresent())
        {
            Department department=new Department(id, departmentDTO.getDepartmentName());
            _departmenDal.save(department);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Optional<Department> FindDepartment(Long id) {
       return _departmenDal.findById(id);
    }

}
