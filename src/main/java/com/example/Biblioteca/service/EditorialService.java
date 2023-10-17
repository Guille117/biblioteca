package com.example.Biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.repository.EditorialRepository;

@Service
public class EditorialService implements IGenericService<Editorial>{

    @Autowired
    private EditorialRepository editRepo;

    @Override
    public void guardar(Editorial t) {
        editRepo.save(t);
    }

    @Override
    public Editorial obtenerUno(Long id) {
       return editRepo.findById(id).orElse(null);
    }

    @Override
    public List<Editorial> obtrnerTodos() {
        return editRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        editRepo.deleteById(id);
    }

    @Override
    public Editorial obtenerPorNombre(String nombre) {
        return editRepo.findByNombre(nombre);
    }
}
