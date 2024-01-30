package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements IGenericService<Categoria>{

    @Autowired
    private CategoriaRepository catRepo;

    @Override
    public void guardar(Categoria t) {
        if(!catRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar categoría
        catRepo.save(t);
        }else{
            throw new ValidacionException("Nombre", "Existe ya un registro de esta categoría");
        }
    }

    @Override
    public Categoria obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
       return catRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada."));
    }

    @Override
    public List<Categoria> obtenerTodos() {
       return catRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        catRepo.deleteById(id);
    }

    public Categoria buscarNombre(String nombreCat){
        return catRepo.findByNombre(nombreCat);
    }

    
}
