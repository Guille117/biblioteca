package com.example.Biblioteca.dto.PrestamoDto;

import com.example.Biblioteca.modelo.Libro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

// atributos de un libro que se mostrarán en un préstamo
public class LibroPresDto {
    private Long idLibro;
    private String nombre;
    private String autor;
    
    public LibroPresDto(Libro lib){
        this.idLibro = lib.getIdLibro();
        this.nombre = lib.getNombre();
        this.autor = lib.getAutor().getNombre() + " " + lib.getAutor().getApellido();
    }
}
