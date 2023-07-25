package com.example.employeemanagementbe.Model.dept_manager;

import com.example.employeemanagementbe.Model.department.Department;
import jakarta.persistence.*;

@Entity
@Table(name="dept_manager")
public class DeptManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_manager_ID")
    private Long deptManagerID;
    private String firstName;

    private String lastName;

    private String email;
    @OneToOne(cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(name = "department_id")
    private Department department;


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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getDeptManagerID() {
        return deptManagerID;
    }

    public DeptManager(String firstName, String lastName, String email, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }

    public DeptManager(Long deptManagerID, String firstName, String lastName, String email, Department department) {
        this.deptManagerID = deptManagerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }

    public DeptManager() {
    }
}
