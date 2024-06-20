package com.titulacion.yaviweb.AuthZ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titulacion.yaviweb.AuthZ.request.loginRequest;
import com.titulacion.yaviweb.AuthZ.request.registerRequest;
import com.titulacion.yaviweb.AuthZ.response.authResponse;
import com.titulacion.yaviweb.AuthZ.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin({"*"})
@RequiredArgsConstructor

public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping(value="login")
    public ResponseEntity<authResponse> login(@RequestBody loginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value="register")
    public ResponseEntity<authResponse> register(@RequestBody registerRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
