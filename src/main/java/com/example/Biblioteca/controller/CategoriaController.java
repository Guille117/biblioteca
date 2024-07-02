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

import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.service.IGenericService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/categoria")
@SecurityRequirement(name = "bearer-key")
public class CategoriaController {
    @Autowired
    private IGenericService<Categoria> catServ;

    @PostMapping
    public ResponseEntity<Categoria> agregarCategoria(@Valid @RequestBody Categoria cat, UriComponentsBuilder ur) {
        catServ.guardar(cat);
        URI url = ur.path("/{idCategoria}").buildAndExpand(cat.getIdCategoria()).toUri();
        return ResponseEntity.created(url).body(catServ.obtenerUno(cat.getIdCategoria()));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> mostrarCategorias(){
        return ResponseEntity.ok().body(catServ.obtenerTodos());
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> mostrarCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok().body(catServ.obtenerUno(idCategoria));
    }

    @PreAuthorize("hasRole('ADMIN')")           // unicamente el adminstrador podra hacer la eliminación
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<?> eliminarCat(@PathVariable Long idCategoria){
        catServ.eliminar(idCategoria);
        return ResponseEntity.noContent().build();
    }
}
