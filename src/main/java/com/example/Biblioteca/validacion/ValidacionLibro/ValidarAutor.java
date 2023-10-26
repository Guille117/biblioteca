package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.AutorRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidarAutor implements IValidarLibro{
    @Autowired
    private AutorRepository autoRepo;

    @Override
    public void validar(Libro lib) {
        boolean existe = false;

        if(lib.getAutor() != null){
            if(lib.getAutor().getIdAutor() != null){
                existe = autoRepo.existsById(lib.getAutor().getIdAutor());
            }
            if(!existe && lib.getAutor().getNombre() == null && lib.getAutor().getApellido() == null){
                throw new ValidationException("No hay registro de este autor, ingrese nombre y apellido de autor para crear registro");
            }
        }
    }
}