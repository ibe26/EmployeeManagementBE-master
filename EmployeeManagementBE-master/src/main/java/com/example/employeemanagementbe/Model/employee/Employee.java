package com.example.employeemanagementbe.Model.employee;

import com.example.employeemanagementbe.Model.department.Department;
import com.example.employeemanagementbe.Model.dept_manager.DeptManager;
import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_id")
    private Long empID;

    private String firstName;

    private String lastName;

    private String email;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="department_id")
    @Transient()
    private Department department;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dept_manager_id")
    private DeptManager deptManager;

    public DeptManager getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(DeptManager deptManager) {
        this.deptManager = deptManager;
    }

    public Long getEmpID() {
        return empID;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public Department getDepartment() {
        Department department1=getDeptManager().getDepartment();
        return department1;
    }

    public Employee() {
    }

    public Employee(Long empID, String firstName, String lastName, String email, DeptManager deptManager) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deptManager = deptManager;
    }

    public Employee(String firstName, String lastName, String email, DeptManager deptManager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deptManager = deptManager;
    }

}
