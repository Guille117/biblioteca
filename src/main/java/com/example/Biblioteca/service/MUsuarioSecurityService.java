package com.example.Biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.MUsuario;
import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.repository.MUsuarioRepository;
import com.example.Biblioteca.repository.ROLRepository;

@Service
public class MUsuarioSecurityService implements UserDetailsService{
    @Autowired
    private MUsuarioRepository usuRepo;
    @Autowired
    private ROLRepository rolRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        MUsuario usuario = usuRepo.findByAlias(username);
        if(usuario == null)throw new UsernameNotFoundException("Usuario no encontrado");

        ROL rol = rolRepo.findById(usuario.getRol().getIdRol()).orElse(null);
        SimpleGrantedAuthority a1 = new SimpleGrantedAuthority("ROLE_".concat(rol.getNombre()));
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(a1);

        return new User(usuario.getAlias(), usuario.getContraseña(), true, true, true, true, authorities);
    }
    
}
