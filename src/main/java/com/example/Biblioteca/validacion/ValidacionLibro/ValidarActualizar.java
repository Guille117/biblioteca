package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@Component
public class ValidarActualizar implements IValidarLibro{
   
    @Autowired
    private LibroRepository libRepo;

    @Override
    public void validar(Libro lib) {
       Libro libro = null;
        if(lib.getIdLibro() != null){
            libro = libRepo.findById(lib.getIdLibro()).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));

            if(libro.getEnPrestamo() > 0){
                throw new ValidationException("Para realizar una actualización, el libro no deberá estar en prestamo");
            }
        }
    }
    
}
