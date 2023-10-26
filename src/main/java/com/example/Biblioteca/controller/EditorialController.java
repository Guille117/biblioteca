package com.example.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.service.IGenericService;

@RestController
@RequestMapping("editorial")
public class EditorialController {
    @Autowired
    private IGenericService<Editorial> ediServ;

    @GetMapping
    public ResponseEntity<List<Editorial>> mostrarEditoriales(){
        return ResponseEntity.ok().body(ediServ.obtenerTodos());
    }

    @GetMapping("/{idEditorial}")
    public ResponseEntity<Editorial> mostrarEditorial(@PathVariable Long idEditorial){
        return ResponseEntity.ok().body(ediServ.obtenerUno(idEditorial));
    }

}
