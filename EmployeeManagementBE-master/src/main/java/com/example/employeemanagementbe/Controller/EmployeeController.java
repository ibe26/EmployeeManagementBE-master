package com.example.employeemanagementbe.Controller;

import com.example.employeemanagementbe.Model.employee.Employee;
import com.example.employeemanagementbe.Model.employee.EmployeeDTO;
import com.example.employeemanagementbe.Service.Abstract.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final IEmployeeService _employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        _employeeService = employeeService;
    }
    @Cacheable("employees")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<Employee>> getEmployees(){
        return new ResponseEntity<Collection<Employee>>(_employeeService.GetEmployees(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long id){
        Boolean employeeExists=!_employeeService.FindEmployee(id).isEmpty();
        if(employeeExists)
        {
            return new ResponseEntity<Employee>(_employeeService.FindEmployee(id).get(),HttpStatus.OK);
        }
        return  new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "employees",allEntries = true)
    @PostMapping("/post")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        return new ResponseEntity<Employee>(_employeeService.AddEmployee(employeeDTO),HttpStatus.OK);
    }
    @CacheEvict(value = "employees",allEntries = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id){
        if(_employeeService.DeleteEmployee(id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "employees",allEntries = true)
    @PutMapping("/put/{id}")
    public ResponseEntity<HttpStatus> putEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDTO employeeDTO){
        if(_employeeService.UpdateEmployee(employeeDTO,id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
