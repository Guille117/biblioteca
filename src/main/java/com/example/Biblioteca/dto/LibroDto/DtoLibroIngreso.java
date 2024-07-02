package com.example.Biblioteca.dto.LibroDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoLibroIngreso {

    @NotNull
    private String nombre;

    @NotNull
    private Long idAutor;

    @NotNull
    private Long idCategoria; 

    @NotNull
    private Long idEditorial;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "\\d+")
    private String anioPublicacion;

    @NotNull
    @Min(1)
    private Integer edicion;

    @NotNull
    @Max(value = 10)
    @Min(value = 1)
    @Schema(description = "Valor entero no mayor a 10")         // anotación para documentacion
    private Integer cantidad;
}
