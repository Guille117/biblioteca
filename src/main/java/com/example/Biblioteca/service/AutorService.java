package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.repository.AutorRepository;

public class AutorService implements IGenericService<Autor>{

    @Autowired
    private AutorRepository autoRepo;

    @Override
    public void guardar(Autor t) {
        autoRepo.save(t);
    }

    @Override
    public Autor obtenerUno(Long id) {
        return autoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Autor> obtrnerTodos() {
       return autoRepo.findAll();
    }

    @Override
    public void eliminar(Long idAutor) {
        autoRepo.deleteById(idAutor);
    }

    @Override
    public Autor obtenerPorNombre(String nombre) {
        return autoRepo.findByNombre(nombre);
    }
    
}
