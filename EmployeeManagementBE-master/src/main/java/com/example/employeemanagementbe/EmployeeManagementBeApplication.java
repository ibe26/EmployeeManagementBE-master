package com.example.employeemanagementbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeManagementBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementBeApplication.class, args);
    }

}
