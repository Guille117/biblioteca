package com.example.Biblioteca.validacion.ValidarPrestamo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.modelo.Prestamo;

@Component
public class ValidarLibro implements IValidarPrestamo{
        // determinamos que no haya libros repetidos en la solicitud de préstamo
    @Override
    public void validar(Prestamo p) {
        List<Libro> libros = p.getLibros();         //obtenemos todos los libros
        Set<Long> verificados = new HashSet<>();        // creamos un set que es tipo de lista que no permite elementos repetidos

        for(Libro l: libros){       // bucle for each
            if(!verificados.add(l.getIdLibro())){       // intentamos agregar, sino se puede nos dará false, entonces lazamos excepción
                throw new ValidacionException("Libros", "No se permite prestar dos o más libros iguales");
            }
        }
    }
    
}
