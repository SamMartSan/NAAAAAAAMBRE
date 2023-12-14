package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.AuthCredentials;
import com.example.demo.security.AuthResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController("/api/v1")
@AllArgsConstructor
public class UsuarioController {

    @PostMapping("authenticate")
    public ResponseEntity<AuthResponse> autenticacion(@RequestBody AuthCredentials authCredentials) {
        //TODO: process POST request
        return ResponseEntity.ok(AuthResponse.builder().build());
    }
    
    @GetMapping("/")
    public String funcion() {
        return "Bienvenido a la aplicacion";
    }
    
}
