package com.example.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.service.IGenericService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/autor")
@SecurityRequirement(name = "bearer-key")
public class AutorController {
    @Autowired
    private IGenericService<Autor> autoServ;
    
    @PostMapping
    public ResponseEntity<Autor> ingresarAutor(@Valid @RequestBody Autor autor, UriComponentsBuilder ur) {
        // Se crea una url donde se puede obtener el elemento ingresado, para luego retornarlo al response entity
        URI url = ur.path("/autor/{idAutor}").buildAndExpand(autor.getIdAutor()).toUri();

        
        autoServ.guardar(autor);
        return ResponseEntity.created(url).body(autoServ.obtenerUno(autor.getIdAutor()));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> mostrarAutores(){
        return ResponseEntity.ok().body(autoServ.obtenerTodos());
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<Autor> mostrarAutor(@PathVariable Long idAutor){
        return ResponseEntity.ok().body(autoServ.obtenerUno(idAutor));
    }

    @DeleteMapping("/{idAutor}")
    public ResponseEntity<?> eliminarAutor(@PathVariable Long idAutor){
        return ResponseEntity.noContent().build();
    }

}
