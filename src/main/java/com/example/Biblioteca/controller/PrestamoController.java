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

import com.example.Biblioteca.dto.DtoPrestamo;
import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoIngreso;
import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.service.servicioPrestamo.IPrestamoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prestamo")
@SecurityRequirement(name = "bearer-key")
public class PrestamoController {
    
    @Autowired
    private IPrestamoService presService;

    @PostMapping
    public ResponseEntity<Prestamo> guardarPres(@Valid @RequestBody DtoPrestamoIngreso pres, UriComponentsBuilder ur){
        Prestamo pres1 = presService.guardarPrestamo(pres);
        URI url = ur.path("/prestamo/{idPrestamo}").buildAndExpand(pres1.getIdPrestamo()).toUri();
        return ResponseEntity.created(url).body(pres1);
    }

    @GetMapping("/{idPrestamo}")
    public ResponseEntity<DtoPrestamo> obtener1(@PathVariable Long idPrestamo){
        return ResponseEntity.ok().body(presService.mostrarPrestamo(idPrestamo));
    }

    @GetMapping()
    public ResponseEntity<List<DtoPrestamo>> obtenerTodos(){
        return ResponseEntity.ok().body(presService.mostrarPrestamos());
    }

    @GetMapping("/activos/{activo}")
    public ResponseEntity<List<DtoPrestamo>> mostrarPorEstadoPrestamo(@PathVariable boolean activo){
        return ResponseEntity.ok().body(presService.mostrarActivos(activo));
    }

    @PutMapping("/finalizarPrestamo/{idPrestamo}")
    public ResponseEntity<?> finalizarPrestamo(@PathVariable Long idPrestamo){
        presService.finalizarPrestamo(presService.obtenerPrestamo(idPrestamo));
        return ResponseEntity.ok().body(presService.mostrarPrestamo(idPrestamo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        presService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
