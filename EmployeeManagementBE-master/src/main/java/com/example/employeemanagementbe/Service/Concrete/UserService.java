package com.example.employeemanagementbe.Service.Concrete;

import com.example.employeemanagementbe.Dal.IUserDal;
import com.example.employeemanagementbe.Model.user.CredentialsDto;
import com.example.employeemanagementbe.Model.user.SignUpDto;
import com.example.employeemanagementbe.Model.user.User;
import com.example.employeemanagementbe.Model.user.UserDto;
import com.example.employeemanagementbe.Service.Abstract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserDal userDal;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(IUserDal userDal, PasswordEncoder passwordEncoder) {
        this.userDal = userDal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        Optional<User> user = userDal.findByLogin(credentialsDto.login());
        if(user.isEmpty()){
            return null;
        }
        if (passwordEncoder.matches(CharBuffer.wrap((credentialsDto.password())), user.get().getpassword())) {
        return new UserDto(user.get().getId(), user.get().getFirstName(), user.get().getLastName(), user.get().getLogin());
        } else return null;
    }
    public Optional<User> findByLogin(String login){
        Optional<User> foundUser= userDal.findAll().stream().filter(u->u.getLogin().equals(login)).findFirst();
        if(foundUser.isPresent()){
            return foundUser;
        }
        else return null;
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser=userDal.findByLogin(signUpDto.login());
        if(oUser.isPresent()){
            return null;
        }
        User user=new User(signUpDto.firstName(),signUpDto.lastName(),signUpDto.login(), passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        UserDto userDto=new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin());
        userDal.save(user);
        return userDto;
    }
}
