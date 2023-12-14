package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    private Long id;
    private String username;
    private String password;

    
    public String getContrasena() {
        return this.password;
    }
    public String getUsuario() {
        return this.username;
    }
}
