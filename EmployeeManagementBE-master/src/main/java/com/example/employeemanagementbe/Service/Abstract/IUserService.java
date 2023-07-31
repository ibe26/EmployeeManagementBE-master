package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.user.SignUpDto;
import com.example.employeemanagementbe.Model.user.User;
import com.example.employeemanagementbe.Model.user.CredentialsDto;
import com.example.employeemanagementbe.Model.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignUpDto signUpDto);
    Optional<User> findByLogin(String login);
}
