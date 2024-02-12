package com.pvh.group3_spring_mini_project001.service.serviceImp;

import com.pvh.group3_spring_mini_project001.config.PasswordEncoderConfig;
import com.pvh.group3_spring_mini_project001.exception.DuplicateEmail;
import com.pvh.group3_spring_mini_project001.exception.NotFoundValidate;
import com.pvh.group3_spring_mini_project001.exception.ValidateEmail;
import com.pvh.group3_spring_mini_project001.exception.ValidatePassword;
import com.pvh.group3_spring_mini_project001.mapper.UserMapper;
import com.pvh.group3_spring_mini_project001.model.User;
import com.pvh.group3_spring_mini_project001.model.dto.UserDto;
import com.pvh.group3_spring_mini_project001.model.request.UserRequest;
import com.pvh.group3_spring_mini_project001.model.validate.Validate;
import com.pvh.group3_spring_mini_project001.repository.UserRepository;
import com.pvh.group3_spring_mini_project001.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserServiceImpl(UserRepository repository, PasswordEncoderConfig passwordEncoderConfig) {
        this.repository = repository;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user=repository.getUserByEmail(email);
        if (user==null){
            throw new NotFoundValidate("Not found user");
        }
        return user;
    }

    //register validate password
    @Override
    public UserDto register(UserRequest request) {
        if (!Validate.validatePassword(request.getPassword())){
            throw new ValidatePassword("Password should be at least 8 character and 1 special character Uppercase and Lowercase character and No Space");
        } else if (!Validate.validateEmail(request.getEmail())) {
            throw  new ValidateEmail("Email should be like this -> somthing@somthing.com");
        } else if (repository.getUserByEmail(request.getEmail())!= null) {
            throw new DuplicateEmail("This Email is already taken ! Please enter another Email!");

        }
        request.setPassword(passwordEncoderConfig.encoder().encode(request.getPassword()));
        User user=repository.register(request);
        return UserMapper.INSTANCE.toUserDTO(user);
    }
    @Override
    public UserDto getUserByEmail(String email) {
       User user= repository.getUserByEmail(email);
        return UserMapper.INSTANCE.toUserDTO(user);
    }
}
