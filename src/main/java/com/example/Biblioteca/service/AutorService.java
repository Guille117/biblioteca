package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoEnUsoException;
import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.repository.AutorRepository;
import com.example.Biblioteca.repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorService implements IGenericService<Autor>{

    @Autowired
    private AutorRepository autoRepo;
    @Autowired
    private LibroRepository libroRepo;

    @Override
    public void guardar(Autor t) {
        if(!autoRepo.existsByNombreAndApellido(t.getNombre(),t.getApellido())){     // si se agrega otra validación, crear una clase especial para validar autor 
            autoRepo.save(t);
        }else{
                throw new ElementoRepetidoException("Nombre, Apellido","Ya existe registro de este autor.");
            }
        
    }

    @Override
    public Autor obtenerUno(Long id) {
        if(id == null){
            throw new RuntimeException();
        }
        return autoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    }

    @Override
    public List<Autor> obtenerTodos() {
       return autoRepo.findAll();
    }

    @Override
    public void eliminar(Long idAutor) {
        if(idAutor != null){
            if(libroRepo.existsByAutorIdAutor(idAutor)){
                throw new ElementoEnUsoException("ID", "No es posible eliminar este autor porque está siendo referenciado en la sección de libros");
            }else{
                this.obtenerUno(idAutor);           // en caso no exista nos arroje una exception ya definida en el metodo obtener 1
                autoRepo.deleteById(idAutor); 
            }
           
        }
    }
    
}
