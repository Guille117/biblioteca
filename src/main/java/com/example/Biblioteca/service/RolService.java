package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.repository.ROLRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RolService implements IGenericService<ROL>{
    @Autowired
    private ROLRepository rolRepo;


    @Override
    public void guardar(ROL t) {
        if(!rolRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar rol
            rolRepo.save(t);
        }else{
            throw new ValidacionException("Nombre", "Existe ya un registro de este rol");
        }
    }

    @Override
    public ROL obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return rolRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado."));
    }

    @Override
    public List<ROL> obtenerTodos() {
        return rolRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        rolRepo.deleteById(id);
    }
    
}
