package com.app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //h(CD)2
      http.csrf().disable().cors().disable();

      //haap
    http.authorizeHttpRequests().anyRequest().permitAll();
      http.addFilterBefore(jwtFilter ,AuthorizationFilter.class);

     /* http.authorizeHttpRequests()
              .requestMatchers("/api/v1/auth/signup","/api/v1/auth/userSignin","/api/v1/auth/content-manager-signup","/api/v1/auth/blog-manager-signup","/api/v1/auth/login-otp","/api/v1/auth/validate-otp")
              .permitAll()
              .requestMatchers("/api/v1/cars/add-car").hasRole("CONTENTMANAGER")
              .anyRequest().authenticated();*/
      return http.build();
    }
}
