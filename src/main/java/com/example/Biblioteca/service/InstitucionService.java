package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.repository.InstitucionRepository;

@Service
public class InstitucionService implements IGenericService<Institucion>{

    @Autowired
    private InstitucionRepository insRepo;

    @Override
    public void guardar(Institucion t) {
        insRepo.save(t);
    }

    @Override
    public Institucion obtenerUno(Long id) {
        return insRepo.findById(id).orElse(null);
    }

    @Override
    public List<Institucion> obtenerTodos() {
        return insRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        insRepo.deleteById(id);
    }

}
