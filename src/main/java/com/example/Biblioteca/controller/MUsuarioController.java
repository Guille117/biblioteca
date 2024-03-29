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
import com.example.Biblioteca.modelo.MUsuario;
import com.example.Biblioteca.service.MUsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearer-key")
public class MUsuarioController {
    @Autowired
    private MUsuarioService usuServ;

    @PostMapping
    public ResponseEntity<MUsuario> guardarUsuario(@Valid @RequestBody MUsuario usu, UriComponentsBuilder ur){
        usuServ.guardar(usu);
        URI url = ur.path("/usuario/{idUsuario}").buildAndExpand(usu.getIdUsuario()).toUri();
        return ResponseEntity.created(url).body(usu);
    }

    @GetMapping
    public ResponseEntity<List<MUsuario>> mostrar(){
        return ResponseEntity.ok().body(usuServ.obtenerTodos());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MUsuario> mostrar1(@PathVariable Long idUsuario){
        return ResponseEntity.ok().body(usuServ.obtenerUno(idUsuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> eliminar(@PathVariable Long idUsuario){
        usuServ.eliminar(idUsuario);
        return ResponseEntity.noContent().build();
    }


}
