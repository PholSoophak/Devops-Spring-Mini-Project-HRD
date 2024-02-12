package com.pvh.group3_spring_mini_project001.service;

import com.pvh.group3_spring_mini_project001.model.dto.UserDto;
import com.pvh.group3_spring_mini_project001.model.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto register(UserRequest request);
    UserDto getUserByEmail(String email);

}
