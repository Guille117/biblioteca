package com.example.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Biblioteca.dto.DtoLibro;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.servicioLibro.ILibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("libro")
public class LibroController {
    
    @Autowired
    private ILibroService libServ;

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Libro lib, UriComponentsBuilder ur){
        libServ.guardar(lib);
        URI url = ur.path("/libro/{idLibro}").buildAndExpand(lib.getIdLibro()).toUri();

        return ResponseEntity.created(url).body(lib);
    }

    @GetMapping("/{idLibro}")
    public ResponseEntity<Libro> mostrar1(@PathVariable Long idLibro){
        return ResponseEntity.ok().body(libServ.obtenerUno(idLibro));
    } 

    @GetMapping
    public ResponseEntity<List<Libro>> mostrarT(){
        return ResponseEntity.ok().body(libServ.obtenerTodos());
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Libro>> mostrarPorCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok().body(libServ.MostrarPorCategoria(idCategoria));
    }

    @GetMapping("/editorial/{idEditorial}")
    public ResponseEntity<List<Libro>> mostrarPorEditorial(@PathVariable Long idEditorial){
        return ResponseEntity.ok().body(libServ.MostrarPorEditorial(idEditorial));
    }

    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<List<Libro>> mostrarPorAutor(@PathVariable Long idAutor){
        return ResponseEntity.ok().body(libServ.MostrarPorAutor(idAutor));
    }

    @GetMapping("/enPrestamo")
    public ResponseEntity<List<Libro>> mostrarEnPrestamo(){
        return ResponseEntity.ok().body(libServ.MostrarEnPrestamo());
    }


    @PutMapping
    public ResponseEntity<Libro> actualizar(@Valid @RequestBody DtoLibro lib){

        libServ.actualizarLibro(lib);
        return ResponseEntity.ok().body(libServ.obtenerUno(lib.getIdLibro()));
    }

    @DeleteMapping("/{idLibro}")
    public ResponseEntity<?> eliminar(@PathVariable Long idLibro){
        libServ.eliminar(idLibro);
        return ResponseEntity.noContent().build();
    }
}
