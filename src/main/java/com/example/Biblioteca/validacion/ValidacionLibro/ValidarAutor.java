package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.IGenericService;

import jakarta.validation.ValidationException;

@Component
public class ValidarAutor implements IValidarLibro{
    @Autowired
    private IGenericService<Autor> autorService;

    @Override
    public void validar(Libro lib) {
        Autor autor = null;
        if(lib.getAutor().getIdAutor() != null){
            autor = autorService.obtenerUno(lib.getAutor().getIdAutor());
        }
        if(autor ==null){
            if(lib.getAutor().getNombre() == null && lib.getAutor().getApellido() == null){
                throw new ValidationException("No hay registro de este autor, ingrese nombre y apellido de autor para crear registro");
            }
        }
        

    }
    
}
