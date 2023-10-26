package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.repository.AutorRepository;

@Service
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
    public List<Autor> obtenerTodos() {
       return autoRepo.findAll();
    }

    @Override
    public void eliminar(Long idAutor) {
        autoRepo.deleteById(idAutor);
    }

    public Autor buscarNombre(String nombre, String apellido){
        return autoRepo.findByNombreAndApellido(nombre, apellido);
    }
    
}
