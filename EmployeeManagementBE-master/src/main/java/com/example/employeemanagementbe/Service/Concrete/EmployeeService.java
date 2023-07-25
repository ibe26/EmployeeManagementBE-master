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
        Department department=_departmentDal.findById(employeeDTO.getDepartmentID()).get();
        DeptManager deptManager=_deptManagerDal.findAll().stream().filter(d->d.getDepartment().getDepartmentID()==department.getDepartmentID()).findFirst().get();
        Employee employee=new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail(), deptManager);
        return _employeeDal.save(employee);

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
        if(_employeeDal.existsById(id))
        {
            Department department=_departmentDal.findById(employeeDTO.getDepartmentID()).get();
            DeptManager deptManager=_deptManagerDal.findAll().stream().filter(d->d.getDepartment().getDepartmentID()==department.getDepartmentID()).findFirst().get();
            Employee existingEmployee=new Employee(id,employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail(),deptManager);
            _employeeDal.save(existingEmployee);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Collection<Employee> GetEmployees() {
       return _employeeDal.findAll();

    }

    @Override
    public Optional<Employee> FindEmployee(Long id) {
        return _employeeDal.findById(id);
    }
}
