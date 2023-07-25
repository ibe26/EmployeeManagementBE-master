package com.example.employeemanagementbe.Controller;

import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import com.example.employeemanagementbe.Model.dept_manager.DeptManagerDTO;
import com.example.employeemanagementbe.Service.Abstract.IDeptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/dept_manager")
@CrossOrigin(origins = "*")
public class DeptManagerController {
    private final IDeptManagerService _deptManagerService;

    @Autowired
    public DeptManagerController(IDeptManagerService deptManagerService) {
        _deptManagerService = deptManagerService;
    }

    @Cacheable("deptManagers")
    @GetMapping("getAll")
    public ResponseEntity<Collection<DeptManager>> GetDeptManagers(){
        return new ResponseEntity<Collection<DeptManager>>(_deptManagerService.GetDeptManagers(),HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<?> FindDeptManager(@PathVariable("id") Long id){
        if(!_deptManagerService.FindDeptManager(id).isEmpty())
        {
            return new ResponseEntity<DeptManager>(_deptManagerService.FindDeptManager(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @PostMapping("post")
    public ResponseEntity<DeptManager> PostDeptManager(@RequestBody DeptManagerDTO deptManagerDTO){
        return new ResponseEntity<DeptManager>(_deptManagerService.AddDeptManager(deptManagerDTO),HttpStatus.OK);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> DeleteDeptManager(@PathVariable("id") Long id){
        if(_deptManagerService.DeleteDeptManager(id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @PutMapping("put/{id}")
    public ResponseEntity<HttpStatus> PutDeptManager(@PathVariable("id") Long id,@RequestBody DeptManagerDTO deptManagerDTO){
        if(_deptManagerService.UpdateDeptManager(deptManagerDTO,id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return  new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
