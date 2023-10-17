package com.example.Biblioteca.service.servicioLector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.LectorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LectorService implements ILectorService{

    @Autowired
    private LectorRepository lecRepo;

    @Override
    public void guardar(Lector t) {
        lecRepo.save(t);
    }

    @Override
    public Lector obtenerUno(Long id) {
        return lecRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado."));
    }

    @Override
    public List<Lector> obtrnerTodos() {
        return lecRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        lecRepo.deleteById(id);
    }

    @Override
    public Lector obtenerPorNombre(String nombre) {
        return lecRepo.findByNombre(nombre);
    }
    
}
