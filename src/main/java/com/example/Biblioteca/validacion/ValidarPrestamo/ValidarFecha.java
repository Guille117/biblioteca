package com.example.Biblioteca.validacion.ValidarPrestamo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Prestamo;

@Component
public class ValidarFecha implements IValidarPrestamo{

    // validamos la cantidad de días que es posible prestar un libro
    @Override
    public void validar(Prestamo p) {
        LocalDate fecha = p.getFechaVencimiento();
        // fecha anterior a la actual           // si la fecha sobrepasa el limite de 5 dias plazo 
        if(fecha.isBefore(LocalDate.now()) || fecha.isAfter(LocalDate.now().plusDays(5))){
            throw new ValidacionException("Fecha de vencimiento", "Únicamente se permite prestar los libros por un plazo de 5 días");
        }
    }
    
}
