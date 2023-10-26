package com.example.Biblioteca.validacion.ValidacionLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.LibroRepository;
import com.example.Biblioteca.service.IGenericService;

import jakarta.validation.ValidationException;

@Component
public class ValidarExistencia /*implements IValidarLibro*/{
    @Autowired
    private LibroRepository libRepo;
    @Autowired
    private IGenericService<Autor> autService;
    @Autowired
    private IGenericService<Editorial> edService;
    @Autowired
    private IGenericService<Categoria> catService;


    public void validar(Libro lib) {
        Libro libro = null;
        libro = libRepo.findByNombreAndEdicion(lib.getNombre(),lib.getEdicion());
        
        if(libro != null){
            Autor autorPivote = null;
            Editorial ediPivote = null;
            Categoria catPivote = null;

            try{
                autorPivote = autService.obtenerUno(lib.getAutor().getIdAutor());
                ediPivote = edService.obtenerUno(lib.getEditorial().getIdEditorial());
                catPivote = catService.obtenerUno(lib.getCategoria().getIdCategoria());
            }catch(Exception v2){  
            }
            
            if(libro.getAutor() == autorPivote){
                if(libro.getCategoria() == catPivote){
                    if(libro.getEditorial() == ediPivote){
                        if(libro.getIdLibro() != lib.getIdLibro()){
                            throw new ValidationException("Este libro ya existe");                            
                        }
                    }
                }
            }
        }
    }
    
}
 