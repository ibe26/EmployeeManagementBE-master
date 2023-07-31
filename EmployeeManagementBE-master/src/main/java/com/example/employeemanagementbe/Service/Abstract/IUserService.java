package com.example.employeemanagementbe.Service.Abstract;

import com.example.employeemanagementbe.Model.user.CredentialsDto;
import com.example.employeemanagementbe.Model.user.SignUpDto;
import com.example.employeemanagementbe.Model.user.User;
import com.example.employeemanagementbe.Model.user.UserDto;

import java.util.Optional;

public interface IUserService {
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignUpDto signUpDto);
    Optional<User> findByLogin(String login);
}
