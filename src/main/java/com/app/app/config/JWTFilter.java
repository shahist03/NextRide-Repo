package com.app.app.config;

import com.app.app.entity.User;
import com.app.app.repository.UserRepository;
import com.app.app.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public JWTFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                String jwtToken = token.substring(7); // Corrected substring index

                // Verify and validate JWT token
                String username = jwtService.getUsername(jwtToken);

                if (username != null) {
                    Optional<User> optionalUser = userRepository.findByUsername(username);

                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
                                );

                        authenticationToken.setDetails(new WebAuthenticationDetails(request));

                        // Ensure response is not committed before setting security context
                        if (!response.isCommitted()) {
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Log the error and send a proper response if needed
            logger.error("JWT Authentication failed: " + e.getMessage());

            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
