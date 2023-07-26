package com.example.employeemanagementbe.Dal;

import com.example.employeemanagementbe.Model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDal extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
}
