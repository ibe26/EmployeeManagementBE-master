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
    public ResponseEntity<Collection<DeptManager>> getDeptManagers(){
        return new ResponseEntity<Collection<DeptManager>>(_deptManagerService.getDeptManagers(),HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<?> findDeptManager(@PathVariable("id") Long id){
        if(!_deptManagerService.findDeptManager(id).isEmpty())
        {
            return new ResponseEntity<DeptManager>(_deptManagerService.findDeptManager(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @PostMapping("post")
    public ResponseEntity<DeptManager> postDeptManager(@RequestBody DeptManagerDTO deptManagerDTO){
        return new ResponseEntity<DeptManager>(_deptManagerService.addDeptManager(deptManagerDTO),HttpStatus.OK);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteDeptManager(@PathVariable("id") Long id){
        if(_deptManagerService.deleteDeptManager(id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(value = "deptManagers",allEntries = true)
    @PutMapping("put/{id}")
    public ResponseEntity<HttpStatus> putDeptManager(@PathVariable("id") Long id,@RequestBody DeptManagerDTO deptManagerDTO){
        if(_deptManagerService.updateDeptManager(deptManagerDTO,id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return  new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
