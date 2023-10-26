package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.EditorialRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidarEditorial implements IValidarLibro{
   @Autowired
   private EditorialRepository ediRepo;

   @Override
   public void validar(Libro lib) {
      boolean existe = false;

      if(lib.getEditorial() != null){
         if(lib.getEditorial().getIdEditorial() != null){
            existe = ediRepo.existsById(lib.getEditorial().getIdEditorial());
         }
         if(!existe && lib.getEditorial().getNombre() == null){
            throw new ValidationException("No existe registro de esta editorial, ingrese nombre de editorial para crear registro.");
         }
      }
   }
}
