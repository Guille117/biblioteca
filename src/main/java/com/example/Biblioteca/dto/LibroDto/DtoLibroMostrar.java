package com.example.Biblioteca.dto.LibroDto;

import com.example.Biblioteca.modelo.Libro;

public record DtoLibroMostrar(
    Long idLibro,
    String nombreLibro,
    String nombreAutor,
    String Categoria,
    String Editorial,
    Integer edicion,
    Integer cantidad,
    Integer disponible,
    Integer enPrestamo,
    String anioPublicacion,
    String fechaIngreso
) {

    
    public DtoLibroMostrar(Libro lib){
        this(
        lib.getIdLibro(),
        lib.getNombre(),
        lib.getAutor().nombreCompleto(), 
        lib.getCategoria().getNombre(), 
        lib.getEditorial().getNombre(), 
        lib.getEdicion(), 
        lib.getCantidad(), 
        lib.getDisponible(), 
        lib.getEnPrestamo(), 
        lib.getAnioPublicacion(), 
        lib.getFechaIngreso().toString()
        );
    }    
}

        
