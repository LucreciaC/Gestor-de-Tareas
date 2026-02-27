package com.hibernate.tareas.controller;

import com.hibernate.tareas.DTO.RegisterDTO;
import com.hibernate.tareas.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        authService.register(dto);
        return new ResponseEntity<>("Usuario creado OK", HttpStatus.CREATED);
    }
}