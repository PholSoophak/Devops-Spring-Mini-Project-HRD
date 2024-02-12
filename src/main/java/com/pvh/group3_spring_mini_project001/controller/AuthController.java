package com.pvh.group3_spring_mini_project001.controller;

import com.pvh.group3_spring_mini_project001.jwt.JwtTokenUtil;
import com.pvh.group3_spring_mini_project001.model.dto.UserDto;
import com.pvh.group3_spring_mini_project001.model.request.UserLoginRequest;
import com.pvh.group3_spring_mini_project001.model.request.UserRequest;
import com.pvh.group3_spring_mini_project001.response.Response;
import com.pvh.group3_spring_mini_project001.service.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/api/auth")

public class AuthController {
private final UserService service;
private final JwtTokenUtil jwtTokenUtil;
private final AuthenticationManager authenticationManager;
    public AuthController(UserService service, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("signup")
    public ResponseEntity<Response<UserDto>>register(@RequestBody UserRequest request){
        Response<UserDto> response=Response.<UserDto>builder()
                .payload(service.register(request))
                .status(true)
                .dateTime(LocalDateTime.now()).build();
        return ResponseEntity.ok().body(response);
    }
   @PostMapping("signin")
    public ResponseEntity<Response<UserDto>> createAuthenticationToken(@RequestBody UserLoginRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = service
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        UserDto userDto=service.getUserByEmail(authenticationRequest.getEmail());
        userDto.setToken(token);
       Response<UserDto> response=Response.<UserDto>builder()
               .payload(userDto)
               .status(true)
               .dateTime(LocalDateTime.now()).build();

        return ResponseEntity.ok().body(response);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

