package com.app.app.controller;


import com.app.app.entity.User;
import com.app.app.payload.JWTTokenDto;
import com.app.app.payload.LoginDto;
import com.app.app.repository.UserRepository;
import com.app.app.service.JWTService;
import com.app.app.service.OTPService;
import com.app.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;

    private UserService userService;
    private OTPService otpService;
    private JWTService jwtService;

   // private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, UserService userService, OTPService otpService, JWTService jwtService) {
        this.userRepository = userRepository;
      //  this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user)
    {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if(byUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> byEmail = userRepository.findByEmailId(user.getEmailId());
        if(byEmail.isPresent()) {
            return new ResponseEntity<>("email id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Logic to create a new user in the database

        // Encrypting password before saving to the database first method
    /*    String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);*/


        // Second method for encrypting password before saving to the database
        // Using BCryptPasswordEncoder for encryption
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManagerAccount(
            @RequestBody User user)
    {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if(byUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> byEmail = userRepository.findByEmailId(user.getEmailId());
        if(byEmail.isPresent()) {
            return new ResponseEntity<>("email id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Logic to create a new user in the database

        // Encrypting password before saving to the database first method
    /*    String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);*/


        // Second method for encrypting password before saving to the database
        // Using BCryptPasswordEncoder for encryption
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManagerAccount(
            @RequestBody User user)
    {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if(byUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> byEmail = userRepository.findByEmailId(user.getEmailId());
        if(byEmail.isPresent()) {
            return new ResponseEntity<>("email id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Logic to create a new user in the database

        // Encrypting password before saving to the database first method
    /*    String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);*/

        // Second method for encrypting password before saving to the database
        // Using BCryptPasswordEncoder for encryption
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    @PostMapping("/userSignin")
    public ResponseEntity<?> verifyUser(
            @RequestBody LoginDto loginDto
    ){

        String jwtToken=userService.verifyLogin(loginDto);
        if(jwtToken!=null){
            JWTTokenDto jwtTokenDto = new JWTTokenDto();
            jwtTokenDto.setToken(jwtToken);
            jwtTokenDto.setTokenType("jwt");
            return new ResponseEntity<>(jwtTokenDto ,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login-otp")
   public String generateOtp(
           @RequestParam String mobile)
   {
       Optional<User> byUser = userRepository.findByMobile(mobile);
       if(byUser.isPresent()){
           String otp = otpService.generateOTP(mobile);
           return otp+ "  "+mobile;
       }
       return "User not found";

   }

    @PostMapping("/validate-otp")
    public String validateOtp(
            @RequestParam String mobile,
            @RequestParam String otp
    ) {
        boolean status = otpService.validateOTP(mobile, otp);
        if (status) {
            Optional<User> byUser = userRepository.findByMobile(mobile);
            if (byUser.isPresent()) {
                String jwtToken = jwtService.generateToken(byUser.get().getUsername());
                return jwtToken;
            }
        }

        return status ? "OTP validated successfully" : "Invalid OTP";

    }


}
