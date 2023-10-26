package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.repository.ROLRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RolService implements IGenericService<ROL>{
    @Autowired
    private ROLRepository rolRepo;


    @Override
    public void guardar(ROL t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public ROL obtenerUno(Long id) {
        return rolRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
    }

    @Override
    public List<ROL> obtenerTodos() {
        return rolRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        rolRepo.deleteById(id);
    }
    
}
