package com.example.employeemanagementbe.Controller;

import com.example.employeemanagementbe.Model.user.CredentialsDto;
import com.example.employeemanagementbe.Model.user.SignUpDto;
import com.example.employeemanagementbe.Model.user.UserDto;
import com.example.employeemanagementbe.Service.Abstract.IUserService;
import com.example.employeemanagementbe.security.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final IUserService _userService;
    private final UserAuthProvider _userAuthProvider;

    @Autowired
    public AuthController(IUserService userService, UserAuthProvider userAuthProvider) {
        _userService = userService;
        _userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDto credentialsDto){
        UserDto userDto=_userService.login(credentialsDto);
        if(userDto==null){
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
        String token=_userAuthProvider.generateJwtToken(userDto);
        userDto.setToken(token);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody SignUpDto signUpDto){
        UserDto userDto=_userService.register(signUpDto);
        if(userDto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token){
       if(_userAuthProvider.validateJwtToken(token)){
           return new ResponseEntity<>(true,HttpStatus.OK);
       }
       return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

    }

}
