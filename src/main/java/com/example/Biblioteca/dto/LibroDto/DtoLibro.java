package com.example.Biblioteca.dto.LibroDto;

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
    private Long idAutor;
    private Long idCategoria; 
    private Long idEditorial;

    @Size(min = 4, max = 4)
    @Pattern(regexp = "\\d+")
    private String anioPublicacion;

    private Integer edicion;

    @Min(value =  1)
    @Max(value =  10)
    private Integer cantidad;

}
