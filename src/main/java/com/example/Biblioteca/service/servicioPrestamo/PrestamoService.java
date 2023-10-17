package com.example.Biblioteca.service.servicioPrestamo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.repository.PrestamoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrestamoService implements IPrestamoService{

    @Autowired
    private PrestamoRepository presRepo;

    @Override
    public void guardar(Prestamo t) {
        presRepo.save(t);
    }

    @Override
    public Prestamo obtenerUno(Long id) {
       return presRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Prestamo no encontrado"));
    }

    @Override
    public List<Prestamo> obtrnerTodos() {
        return presRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        presRepo.deleteById(id);
    }

    @Override
    public Prestamo obtenerPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorNombre'");
    }
    
}
