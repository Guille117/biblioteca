package com.example.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Biblioteca.dto.LibroDto.DtoLibro;
import com.example.Biblioteca.dto.LibroDto.DtoLibroIngreso;
import com.example.Biblioteca.dto.LibroDto.DtoLibroMostrar;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.servicioLibro.ILibroService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("libro")
@SecurityRequirement(name = "bearer-key")
public class LibroController {
    
    @Autowired
    private ILibroService libServ;

    @PostMapping
    public ResponseEntity<DtoLibroMostrar> guardar(@Valid @RequestBody DtoLibroIngreso lib, UriComponentsBuilder ur){
        Libro l = libServ.guardar(lib);
        System.out.println("hey");
        URI url = ur.path("/libro/{idLibro}").buildAndExpand(l.getIdLibro()).toUri();

        return ResponseEntity.created(url).body(new DtoLibroMostrar(l));
    }

    @GetMapping("/{idLibro}")
    public ResponseEntity<DtoLibroMostrar> mostrar1(@PathVariable Long idLibro){
        return ResponseEntity.ok().body(libServ.obtenerUno(idLibro));
    } 

    @GetMapping
    public ResponseEntity<List<DtoLibroMostrar>> mostrarT(){
        return ResponseEntity.ok().body(libServ.obtenerTodos());
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<DtoLibroMostrar>> mostrarPorCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok().body(libServ.MostrarPorCategoria(idCategoria));
    }

    @GetMapping("/editorial/{idEditorial}")
    public ResponseEntity<List<DtoLibroMostrar>> mostrarPorEditorial(@PathVariable Long idEditorial){
        return ResponseEntity.ok().body(libServ.MostrarPorEditorial(idEditorial));
    }

    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<List<DtoLibroMostrar>> mostrarPorAutor(@PathVariable Long idAutor){
        return ResponseEntity.ok().body(libServ.MostrarPorAutor(idAutor));
    }

    @GetMapping("/enPrestamo")
    public ResponseEntity<List<DtoLibroMostrar>> mostrarEnPrestamo(){
        return ResponseEntity.ok().body(libServ.MostrarEnPrestamo());
    }


    @PutMapping
    public ResponseEntity<DtoLibroMostrar> actualizar(@Valid @RequestBody DtoLibro lib){

        libServ.actualizarLibro(lib);
        return ResponseEntity.ok().body(libServ.obtenerUno(lib.getIdLibro()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idLibro}")
    public ResponseEntity<?> eliminar(@PathVariable Long idLibro){
        libServ.eliminar(idLibro);
        return ResponseEntity.noContent().build();
    }


}
