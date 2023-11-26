package com.example.Biblioteca.validacion.ValidarPrestamo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Prestamo;

@Component
public class ValidarFecha implements IValidarPrestamo{

    @Override
    public void validar(Prestamo p) {
        LocalDate fecha = p.getFechaVencimiento();

        if(fecha.isBefore(LocalDate.now()) || fecha.isAfter(LocalDate.now().plusDays(5))){
            throw new ValidacionException("Fecha de vencimiento", "Únicamente se permite prestar los libros por un plazo de 5 días");
        }
    }
    
}
