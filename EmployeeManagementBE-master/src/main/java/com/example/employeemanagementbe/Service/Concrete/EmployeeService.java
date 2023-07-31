package com.example.employeemanagementbe.Service.Concrete;

import com.example.employeemanagementbe.Dal.IDepartmentDal;
import com.example.employeemanagementbe.Dal.IDeptManagerDal;
import com.example.employeemanagementbe.Dal.IEmployeeDal;
import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import com.example.employeemanagementbe.Model.employee.Employee;
import com.example.employeemanagementbe.Model.employee.EmployeeDTO;
import com.example.employeemanagementbe.Service.Abstract.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeService  implements IEmployeeService {
    private final IEmployeeDal _employeeDal;
    private  final IDeptManagerDal _deptManagerDal;
    private final IDepartmentDal _departmentDal;

    @Autowired
    public EmployeeService(IEmployeeDal employeeDal, IDeptManagerDal deptManagerDal, IDepartmentDal departmentDal) {
        _employeeDal = employeeDal;
        _deptManagerDal = deptManagerDal;
        _departmentDal = departmentDal;
    }

    @Override
    public Employee AddEmployee(EmployeeDTO employeeDTO) {
        Optional<Department> department=_departmentDal.findById(employeeDTO.getDepartmentID());
        Optional<DeptManager> deptManager=_deptManagerDal.findById(employeeDTO.getDeptManagerID());

        if(deptManager.isPresent() && department.isPresent()){
            Employee employee=new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail(),department.get(), deptManager.get());

            //Department and Department Manager's department must be compatible.
            if(employee.getDepartment().getDepartmentID()!=employee.getDeptManager().getDepartment().getDepartmentID()){
                throw new IllegalStateException("Department manager is not a manager of given department.");
            }
            return _employeeDal.save(employee);
        }
            else return null;
    }

    @Override
    public Boolean DeleteEmployee(Long empID) {
        if(_employeeDal.existsById(empID))
        {
            _employeeDal.deleteById(empID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean UpdateEmployee(EmployeeDTO employeeDTO, Long id) {
        if(_employeeDal.existsById(id)) {
            Optional<Department> department = _departmentDal.findById(employeeDTO.getDepartmentID());
            Optional<DeptManager> deptManager = _deptManagerDal.findById(employeeDTO.getDeptManagerID());
            if (deptManager.isPresent() && department.isPresent()) {
                Employee existingEmployee = new Employee(id, employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail(), deptManager.get(), department.get());
                _employeeDal.save(existingEmployee);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Collection<Employee> GetEmployees() {
       return _employeeDal.findAll();
    }

    @Override
    public Optional<Employee> FindEmployee(Long id) {
        Optional<Employee> foundEmployee = _employeeDal.findById(id);
        if(foundEmployee.isPresent()){
            return foundEmployee;
        }
        else return null;
    }
}
