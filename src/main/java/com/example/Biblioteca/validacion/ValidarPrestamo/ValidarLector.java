package com.example.Biblioteca.validacion.ValidarPrestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.repository.PrestamoRepository;

@Component
public class ValidarLector implements IValidarPrestamo{
    @Autowired
    private PrestamoRepository preRepo;

    @Override
    public void validar(Prestamo p) {
        Prestamo pres = null;
        pres = preRepo.findByLectorIdLectorAndPrestamoActivo(p.getLector().getIdLector(), true);
        if(pres != null && pres.getIdPrestamo() != p.getIdPrestamo()){
            throw new ValidacionException("Lector", "Este lector aún tiene un préstamo activo");
        }
    }
    
}
