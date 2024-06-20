package com.titulacion.yaviweb.AuthZ.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.titulacion.yaviweb.AuthZ.request.loginRequest;
import com.titulacion.yaviweb.AuthZ.request.registerRequest;
import com.titulacion.yaviweb.AuthZ.response.authResponse;
import com.titulacion.yaviweb.Rol.Rol;
import com.titulacion.yaviweb.Rol.RolRepository;
import com.titulacion.yaviweb.User.User;
import com.titulacion.yaviweb.User.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public authResponse login(loginRequest request) {
        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return authResponse.builder()
                .token(token)
                .build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    public authResponse register(registerRequest request) {
        Rol userRole = rolRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found: USER"));
        
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .last_name(request.getLastName())
                .number(request.getNumber())
                .email(request.getEmail())
                .password(encodedPassword)
                .rol(userRole)
                .build();

        userRepository.save(user);

        return authResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
