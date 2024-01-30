package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.repository.AutorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorService implements IGenericService<Autor>{

    @Autowired
    private AutorRepository autoRepo;

    @Override
    public void guardar(Autor t) {
        if(!autoRepo.existsByNombreAndApellido(t.getNombre(),t.getApellido())){     // si se agrega otra validación, crear una clase especial para validar autor 
            autoRepo.save(t);
        }else{
            throw new ValidacionException("Nombre y apellido", "Existe ya un registro de este autor");
        }
        
    }

    @Override
    public Autor obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return autoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    }

    @Override
    public List<Autor> obtenerTodos() {
       return autoRepo.findAll();
    }

    @Override
    public void eliminar(Long idAutor) {
        if(idAutor == null){
            throw new IllegalArgumentException("Debe ingresar id");   
        }
        autoRepo.deleteById(idAutor);
    }

    public Autor buscarNombre(String nombre, String apellido){
        return autoRepo.findByNombreAndApellido(nombre, apellido);
    }
    
}
