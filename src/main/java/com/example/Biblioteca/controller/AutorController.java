package com.example.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.service.IGenericService;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private IGenericService<Autor> autoServ;

    @GetMapping
    public ResponseEntity<List<Autor>> mostrarAutores(){
        return ResponseEntity.ok().body(autoServ.obtenerTodos());
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<Autor> mostrarAutor(@PathVariable Long idAutor){
        return ResponseEntity.ok().body(autoServ.obtenerUno(idAutor));
    }

}
