package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.CategoriaRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidarCategoria implements IValidarLibro{
    @Autowired
    private CategoriaRepository catRepo;

    @Override
    public void validar(Libro lib) {
        boolean existe = false;

       if(lib.getCategoria() != null){
            if( lib.getCategoria().getIdCategoria() != null){
                existe = catRepo.existsById(lib.getCategoria().getIdCategoria());
            }
            if(!existe && lib.getCategoria().getNombre() == null){
                throw new ValidationException("No existe registro de esta categoría, ingrese el nombre de la categoría nueva para crear registro.");
            }
       }
    }
    
}
