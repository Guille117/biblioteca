package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.IGenericService;

import jakarta.validation.ValidationException;

@Component
public class ValidarCategoria implements IValidarLibro{
    @Autowired
    private IGenericService<Categoria> categoriaService;

    @Override
    public void validar(Libro lib) {
        Categoria categoria = null;

       if(lib.getCategoria().getIdCategoria() != null){
            categoria = categoriaService.obtenerUno(lib.getCategoria().getIdCategoria());
       }
       
       if(categoria == null){
        if(lib.getCategoria().getNombre() == null){
            throw new ValidationException("No existe registro de esta categoria, ingrese el nombre de la categoria nueva para crear registro.");
        }
       }
    }
    
}
