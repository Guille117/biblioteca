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

import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.service.IGenericService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("editorial")
@SecurityRequirement(name = "bearer-key")
public class EditorialController {
    @Autowired
    private IGenericService<Editorial> ediServ;

    @PostMapping
    public ResponseEntity<Editorial> agregarEdit(@Valid @RequestBody Editorial edit, UriComponentsBuilder ur) {
        ediServ.guardar(edit);
        URI url = ur.path("/{idEditorial}").buildAndExpand(edit.getIdEditorial()).toUri();
        return ResponseEntity.created(url).body(ediServ.obtenerUno(edit.getIdEditorial()));
    }
    
    @GetMapping
    public ResponseEntity<List<Editorial>> mostrarEditoriales(){
        return ResponseEntity.ok().body(ediServ.obtenerTodos());
    }

    @GetMapping("/{idEditorial}")
    public ResponseEntity<Editorial> mostrarEditorial(@PathVariable Long idEditorial){
        return ResponseEntity.ok().body(ediServ.obtenerUno(idEditorial));
    }

    @PreAuthorize("hasRole('ADMIN')")           // unicamente el adminstrador podra hacer la eliminación
    @DeleteMapping("/{idEditorial}")
    public ResponseEntity<?> eliminarEdit(@PathVariable Long idEditorial){
        ediServ.eliminar(idEditorial);
        return ResponseEntity.noContent().build();
    }

}
