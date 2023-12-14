package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuariorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuariorRepository
        .findOneByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario" + username + "no fue encontrado"));

        return new UserDetailsImpl(usuario);
    }
}
