package com.example.demo.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse
{
    String rol;
};