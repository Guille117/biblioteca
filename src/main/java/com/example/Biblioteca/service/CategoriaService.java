package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService implements IGenericService<Categoria>{

    @Autowired
    private CategoriaRepository catRepo;

    @Override
    public void guardar(Categoria t) {
        catRepo.save(t);
    }

    @Override
    public Categoria obtenerUno(Long id) {
       return catRepo.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> obtrnerTodos() {
       return catRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        catRepo.deleteById(id);
    }

    @Override
    public Categoria obtenerPorNombre(String nombre) {
        return catRepo.findByNombre(nombre);
    }
    
}
