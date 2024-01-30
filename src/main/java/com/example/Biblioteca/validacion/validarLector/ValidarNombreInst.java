package com.example.Biblioteca.validacion.validarLector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.InstitucionRepository;

@Component
public class ValidarNombreInst implements IValidarLector{
    @Autowired
    private InstitucionRepository insRepo;

    @Override
    public void validar(Lector lector) {
        if(lector.getInstitucion() != null){
            if(lector.getInstitucion().getIdInstitucion() == null){
                boolean existe = false;
                if(lector.getInstitucion().getNombre() != null){
                    existe = insRepo.existsByNombre(lector.getInstitucion().getNombre());
                }
                if(!existe) {
                    if(lector.getInstitucion().getDireccion() != null){
                        if(lector.getInstitucion().getDireccion().getNumAvenida() == null || lector.getInstitucion().getDireccion().getNumCalle() == null){
                            throw new ValidacionException("Institución", "No existe registro de esta institución, ingrese nombre y dirección para crear registro");
                        }
                    }else{
                        throw new ValidacionException("Institución", "No existe registro de esta institución, ingrese nombre y dirección para crear registro");
                    }
                }
            }
        }
    }
    
}
