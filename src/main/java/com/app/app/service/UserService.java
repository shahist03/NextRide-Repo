package com.app.app.service;

import com.app.app.entity.User;
import com.app.app.payload.LoginDto;
import com.app.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(
            LoginDto loginDto
    ){
        Optional<User> byUsername= userRepository.findByUsername(loginDto.getUsername());

        if(byUsername.isPresent()){
            User user = byUsername.get();
            if( BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
               return jwtService.generateToken(user.getUsername());
            }
        }
        return null;

    }
}
