package com.example.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.service.IGenericService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private IGenericService<Categoria> catServ;

    @GetMapping
    public ResponseEntity<List<Categoria>> mostrarCategorias(){
        return ResponseEntity.ok().body(catServ.obtenerTodos());
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> mostrarCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok().body(catServ.obtenerUno(idCategoria));
    }
}
