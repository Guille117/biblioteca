package com.example.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
    
    @Autowired
    private RolService rolser;

    @GetMapping("/{idRol}")
    public ResponseEntity<ROL> mostrarRol(@PathVariable Long idRol){
        return ResponseEntity.ok().body(rolser.obtenerUno(idRol));
    }

    @GetMapping
    public ResponseEntity<List<ROL>> mostrarRoles(){
        return ResponseEntity.ok().body(rolser.obtenerTodos());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idRol}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long idRol){
        rolser.eliminar(idRol);
        return ResponseEntity.noContent().build();
    }
    
}
