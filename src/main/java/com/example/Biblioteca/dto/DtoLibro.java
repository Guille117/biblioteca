package com.example.Biblioteca.dto;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.modelo.Editorial;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DtoLibro {
    @NotNull
    private Long idLibro;

    private String nombre;
    private Autor autor;
    private Categoria categoria; 
    private Editorial editorial;

    @Size(min = 4, max = 4)
    @Pattern(regexp = "\\d+")
    private String anioPublicacion;

    private Integer edicion;

    @Min(value =  0)
    @Max(value =  7)
    private Integer cantidad;

}
