package com.example.Biblioteca.validacion.ValidarPrestamo;

import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Prestamo;

@Component
public class ValidarCantidadLibros implements IValidarPrestamo{

    @Override
    public void validar(Prestamo p) {
        if(p.getLibros().size() > 5){
            throw new ValidacionException("Libros", "Únicamente se permite entregar 5 libros por cada préstamo");
        }
    }
    
}
