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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.service.IGenericService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/institucion")
@SecurityRequirement(name = "bearer-key")
public class InstitucionController {
    @Autowired
    private IGenericService<Institucion> instServ;

    @PostMapping
    public ResponseEntity<Institucion> guardarInsti(@Valid @RequestBody Institucion inst, UriComponentsBuilder ur){
        instServ.guardar(inst);
        URI url = ur.path("/{idInstitucion}").buildAndExpand(inst.getIdInstitucion()).toUri();
        return ResponseEntity.created(url).body(instServ.obtenerUno(inst.getIdInstitucion()));
    }

    @GetMapping
    public ResponseEntity<List<Institucion>> mostrarInstituciones(){
        return ResponseEntity.ok().body(instServ.obtenerTodos());
    }

    @GetMapping("/{idInstitucion}")
    public ResponseEntity<Institucion> mostrarInstitucion(@PathVariable Long idInstitucion){
        return ResponseEntity.ok().body(instServ.obtenerUno(idInstitucion));
    }

    @PreAuthorize("hasRole('ADMIN')")           // unicamente el adminstrador podra hacer la eliminación
    @DeleteMapping("/{idInstitucion}")
    public ResponseEntity<?> eliminarInst(@PathVariable Long idInstitucion){
        instServ.eliminar(idInstitucion);
        return ResponseEntity.noContent().build();
    }
}
