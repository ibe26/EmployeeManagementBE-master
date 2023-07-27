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
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserDal _userDal;
    private final PasswordEncoder _passwordEncoder;
    @Autowired
    public UserService(IUserDal userDal, PasswordEncoder passwordEncoder) {
        _userDal = userDal;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        User user = _userDal.findByLogin(credentialsDto.login()).get();
        if (_passwordEncoder.matches(CharBuffer.wrap((credentialsDto.password())), user.getpassword())) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin());
        } else throw new IllegalStateException("password is incorrect");
    }
    public Optional<User> findByLogin(String login){
        return _userDal.findAll().stream().filter(u->u.getLogin().equals(login)).findFirst();
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser=_userDal.findByLogin(signUpDto.login());
        if(oUser.isPresent()){
            throw new IllegalStateException("Login already exists");
        }
        User user=new User(signUpDto.firstName(),signUpDto.lastName(),signUpDto.login(), _passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        UserDto userDto=new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin());
        _userDal.save(user);
        return userDto;
    }



    @Override
    public Collection<User> GetUser() {
        return null;
    }

    @Override
    public User AddUser(CredentialsDto credentialsDto) {
        return null;
    }

    @Override
    public Boolean DeleteUser(Long UserID) {
        return null;
    }

    @Override
    public Boolean UpdateUser(CredentialsDto credentialsDto, Long id) {
        return null;
    }

    @Override
    public Optional<User> FindUser(Long id) {
        return Optional.empty();
    }
}
