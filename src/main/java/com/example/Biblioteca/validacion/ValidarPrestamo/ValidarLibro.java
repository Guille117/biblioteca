package com.example.Biblioteca.validacion.ValidarPrestamo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.modelo.Prestamo;

import jakarta.validation.ValidationException;

@Component
public class ValidarLibro implements IValidarPrestamo{

    @Override
    public void validar(Prestamo p) {
        List<Libro> libros = p.getLibros();
        Set<Long> verificados = new HashSet<>();

        for(Libro l: libros){
            if(!verificados.add(l.getIdLibro())){
                throw new ValidationException("No se permite prestar dos o más libros iguales");
            }
        }
    }
    
}
