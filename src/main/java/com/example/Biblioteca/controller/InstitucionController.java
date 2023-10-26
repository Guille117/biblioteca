package com.example.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.service.IGenericService;

@RestController
@RequestMapping("/institucion")
public class InstitucionController {
    @Autowired
    private IGenericService<Institucion> instiServ;

    @GetMapping
    public ResponseEntity<List<Institucion>> mostrarInstituciones(){
        return ResponseEntity.ok().body(instiServ.obtenerTodos());
    }

    @GetMapping("/{idInstitucion}")
    public ResponseEntity<Institucion> mostrarInstitucion(@PathVariable Long idInstitucion){
        return ResponseEntity.ok().body(instiServ.obtenerUno(idInstitucion));
    }

}
