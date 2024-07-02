package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoEnUsoException;
import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.repository.InstitucionRepository;
import com.example.Biblioteca.repository.LectorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstitucionService implements IGenericService<Institucion>{

    @Autowired
    private InstitucionRepository insRepo;
    @Autowired
    private LectorRepository lecRepo;

    @Override
    public void guardar(Institucion t) {
        if(!insRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar institución
            insRepo.save(t);
        }else{
            throw new ElementoRepetidoException("Nombre", "Existe ya un registro de esta institución.");
        }
    }

    @Override
    public Institucion obtenerUno(Long id) {
        if(id == null){     // no es necesario este condicional, se agrega unicamente para evitar la advertencia
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
        if(id != null){
            if(lecRepo.existsByInstitucionIdInstitucion(id)){
                throw new ElementoEnUsoException("ID", "No es posiboe eliminar esta institución, está siendo referenciada en la sección de lectores");
            }else{
                this.obtenerUno(id);        // lo usamos para en caso no existe lanze un error definido en este método
                insRepo.deleteById(id);
            }
        } 
    }
}
