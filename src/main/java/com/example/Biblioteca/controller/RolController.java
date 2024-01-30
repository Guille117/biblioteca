package com.example.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.service.RolService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rol")
@SecurityRequirement(name = "bearer-key")
public class RolController {
    
    @Autowired
    private RolService rolser;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ROL> agregarRol(@RequestBody ROL rol, UriComponentsBuilder ur) {
        rolser.guardar(rol);
        URI url = ur.path("/{idRol}").buildAndExpand(rol.getIdRol()).toUri();
        
        return ResponseEntity.created(url).body(rolser.obtenerUno(rol.getIdRol()));
    }
    
    @GetMapping
    public ResponseEntity<List<ROL>> obtenerRoles(){
        return ResponseEntity.ok().body(rolser.obtenerTodos());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idRol}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long idRol){
        rolser.eliminar(idRol);
        return ResponseEntity.noContent().build();
    }
    
}
