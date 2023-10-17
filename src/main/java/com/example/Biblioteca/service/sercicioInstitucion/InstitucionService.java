package com.example.Biblioteca.service.sercicioInstitucion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.repository.InstitucionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstitucionService implements IInsitucionService{

    @Autowired
    private InstitucionRepository insRepo;

    @Override
    public void guardar(Institucion t) {
        insRepo.save(t);
    }

    @Override
    public Institucion obtenerUno(Long id) {
        return insRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Institución no encontrada"));
    }

    @Override
    public List<Institucion> obtrnerTodos() {
        return insRepo.findAll();
    }

    @Override
    public void actualizar(Long id) {
    }

    @Override
    public void eliminar(Long id) {
        insRepo.deleteById(id);
    }

    @Override
    public Institucion obtenerPorNombre(String nombre) {
        return insRepo.findByNombre(nombre);
    }
}
