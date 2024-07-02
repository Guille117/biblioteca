package com.example.Biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoEnUsoException;
import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.repository.EditorialRepository;
import com.example.Biblioteca.repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EditorialService implements IGenericService<Editorial>{

    @Autowired
    private EditorialRepository editRepo;
    @Autowired
    private LibroRepository libRepo;

    @Override
    public void guardar(Editorial t) {
        if(!editRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar editorial
            editRepo.save(t);
        }else{
            throw new ElementoRepetidoException("Nombre", "Existe ya un registro de esta editorial");
        }
    }

    @Override
    public Editorial obtenerUno(Long id) {
        if(id == null){         // no es necesario comprobar si es nulo, se agrego if solo para evirtar la advertencia
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
        if(id != null){         // no es necesario comprobar si es nulo, se agrego if solo para evirtar la advertencia
            if(!libRepo.existsByEditorialIdEditorial(id)){
                this.obtenerUno(id);            // en caso no exista nos arroje una exception ya definida en el metodo obtener 1
                editRepo.deleteById(id);
            }else{
                throw new ElementoEnUsoException("ID", "No es posible eliminar esta editorial, está siendo referenciada en la sección de libros");
            }
        }
        
    }
}
