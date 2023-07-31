package com.example.employeemanagementbe.Model.employee;

public class EmployeeDTO {

    private String firstName;

    private String lastName;

    private String email;
    private Long departmentID;
    private Long deptManagerID;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public Long getDeptManagerID() {
        return deptManagerID;
    }
}
