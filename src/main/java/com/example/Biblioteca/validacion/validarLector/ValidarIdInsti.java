package com.example.Biblioteca.validacion.validarLector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.InstitucionRepository;


@Component
public class ValidarIdInsti implements ValidarLector{
     @Autowired
     private InstitucionRepository insRepo;

    @Override
    public void validar(Lector lector) {
        if(lector.getInstitucion() != null){
            boolean existe = false;
            if(lector.getInstitucion().getIdInstitucion() != null){
                existe = insRepo.existsById(lector.getInstitucion().getIdInstitucion());
            }
            if(!existe && lector.getInstitucion().getNombre() == null){
                throw new ValidacionException("Institución", "No existe registro de esta institución, ingrese nombre y dirección para crear registro");
            }
            
        }
    }
    
}
