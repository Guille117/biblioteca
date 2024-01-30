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

import com.example.Biblioteca.dto.DtoLector;
import com.example.Biblioteca.dto.LectorDto.DtoLectorIngreso;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.service.servicioLector.LectorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("lector")
@SecurityRequirement(name = "bearer-key")
public class LectorController {
    
    @Autowired
    private LectorService lecServ;

    @PostMapping
    public ResponseEntity<Lector> guardar(@Valid @RequestBody DtoLectorIngreso lector, UriComponentsBuilder ur){
        Lector l = lecServ.sevaLector(lector);
        URI url = ur.path("lector/{idLector}").buildAndExpand(l.getIdLector()).toUri();
        return ResponseEntity.created(url).body(l);
    }

    @GetMapping("/{idLector}")
    public ResponseEntity<Lector> mostrarLector(@PathVariable Long idLector){
        return ResponseEntity.ok().body(lecServ.obtenerUno(idLector));
    }

    @GetMapping
    public ResponseEntity<List<Lector>> mostrarLectores(){
        return ResponseEntity.ok().body(lecServ.obtenerTodos());
    }

    @GetMapping("/institucion/{idInstitucion}")
    public ResponseEntity<List<Lector>> mostrarPorInstitucion(@PathVariable Long idInstitucion){
        return ResponseEntity.ok().body(lecServ.mostrarPorInsitucion(idInstitucion));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idLector}")
    public ResponseEntity<?> eliminarLector(@PathVariable Long idLector){
        lecServ.eliminar(idLector);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Lector> actualiarLector(@Valid @RequestBody DtoLector lecDto){
        lecServ.actualizarLector(lecDto);
        return ResponseEntity.ok().body(lecServ.obtenerUno(lecDto.getIdLector()));
    }

}
