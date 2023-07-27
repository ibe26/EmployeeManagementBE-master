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
    Collection<User> GetUser();
    User AddUser(CredentialsDto credentialsDto);
    Boolean DeleteUser(Long UserID);
    Boolean UpdateUser(CredentialsDto credentialsDto, Long id);
    Optional<User> FindUser(Long id);
    Optional<User> findByLogin(String login);
}
