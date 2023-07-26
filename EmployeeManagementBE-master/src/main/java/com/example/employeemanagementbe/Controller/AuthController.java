package com.example.employeemanagementbe.Controller;

import com.example.employeemanagementbe.Model.user.CredentialsDto;
import com.example.employeemanagementbe.Model.user.SignUpDto;
import com.example.employeemanagementbe.Model.user.UserDto;
import com.example.employeemanagementbe.Service.Abstract.IUserService;
import com.example.employeemanagementbe.config.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.AuthProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService _userService;
    private final UserAuthProvider _userAuthProvider;

    @Autowired
    public AuthController(IUserService userService, UserAuthProvider userAuthProvider) {
        _userService = userService;
        _userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto userDto=_userService.login(credentialsDto);
        String token=_userAuthProvider.generateJwtToken(userDto);
        userDto.setToken(token);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody SignUpDto signUpDto){
        UserDto userDto=_userService.register(signUpDto);

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    @PostMapping("/validate-token")
    public ResponseEntity<HttpStatus> validateToken(@RequestBody String token){
       if(_userAuthProvider.validateJwtToken(token)){
           return new ResponseEntity<HttpStatus>(HttpStatus.OK);
       }
       return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

    }

}
