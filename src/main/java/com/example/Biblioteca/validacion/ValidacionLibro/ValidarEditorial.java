package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.IGenericService;

import jakarta.validation.ValidationException;

@Component
public class ValidarEditorial implements IValidarLibro{
    @Autowired
    private IGenericService<Editorial> ediService;


    @Override
    public void validar(Libro lib) {
       Editorial editorial = null;
       if(lib.getEditorial().getIdEditorial() != null){
        editorial = ediService.obtenerUno(lib.getEditorial().getIdEditorial());
       }
       if(editorial == null){
        if(lib.getEditorial().getNombre() == null){
            throw new ValidationException("No hay registro de esta editorial, ingrese nombre de editorial para crear registro.");
        }
       }
    }
    
}
