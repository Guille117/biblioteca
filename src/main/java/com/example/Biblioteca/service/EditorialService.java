package com.example.Biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.repository.EditorialRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EditorialService implements IGenericService<Editorial>{

    @Autowired
    private EditorialRepository editRepo;

    @Override
    public void guardar(Editorial t) {
        if(!editRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar editorial
            editRepo.save(t);
        }else{
            throw new ValidacionException("Nombre", "Existe ya un registro de esta editorial");
        }
    }

    @Override
    public Editorial obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
       return editRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Editorial no encontrado."));
    }

    @Override
    public List<Editorial> obtenerTodos() {
        return editRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        editRepo.deleteById(id);
    }

    public Editorial buscarNombre(String nombreEdit){
        return editRepo.findByNombre(nombreEdit);
    }

}
