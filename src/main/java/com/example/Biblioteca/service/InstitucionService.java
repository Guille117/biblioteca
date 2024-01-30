package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.repository.InstitucionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstitucionService implements IGenericService<Institucion>{

    @Autowired
    private InstitucionRepository insRepo;

    @Override
    public void guardar(Institucion t) {
        if(!insRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar institución
        insRepo.save(t);
        }else{
            throw new ValidacionException("Nombre", "Existe ya un registro de esta institución");
        }
    }

    @Override
    public Institucion obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return insRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Institución no encontrada."));
    }

    @Override
    public List<Institucion> obtenerTodos() {
        return insRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        insRepo.deleteById(id);
    }

}
