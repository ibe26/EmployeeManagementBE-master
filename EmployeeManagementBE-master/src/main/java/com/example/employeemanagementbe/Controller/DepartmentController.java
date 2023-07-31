package com.example.employeemanagementbe.Controller;

import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.department.DepartmentDTO;
import com.example.employeemanagementbe.Service.Abstract.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/department")
@CrossOrigin(origins = "*")
public class DepartmentController {
    private final IDepartmentService _departmentService;

    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        _departmentService = departmentService;
    }

    @Cacheable("departments")
    @GetMapping("getAll")
    public ResponseEntity<Collection<Department>> getAllDepartments(){
        return new ResponseEntity<Collection<Department>>(_departmentService.GetDepartment(), HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable("id") Long id){
        Optional<Department> departmentExists = _departmentService.FindDepartment(id);
        if(departmentExists.isPresent())
        {
            return new ResponseEntity<Department>(_departmentService.FindDepartment(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("post")
    @CacheEvict(value = "departments",allEntries = true)
    public ResponseEntity<Department> postDepartment(@RequestBody DepartmentDTO departmentDTO){

         return new ResponseEntity<Department>(_departmentService.AddDepartment(departmentDTO),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    @CacheEvict(value = "departments",allEntries = true)
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") Long id){
        if(_departmentService.DeleteDepartment(id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("put/{id}")
    @CacheEvict(value = "departments",allEntries = true)
    public ResponseEntity<HttpStatus> putDepartment(@PathVariable("id") Long id,@RequestBody DepartmentDTO departmentDTO){
        if(_departmentService.UpdateDepartment(departmentDTO, id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return  new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
