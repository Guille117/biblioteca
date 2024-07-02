package com.example.Biblioteca.dto.LectorDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoLectorIngreso {

    @NotNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ\s]+$", message = "Los nombres no deben contener caracteres especiales")
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ\s]+$", message = "Los apellidos no deben contener caracteres especiales")
    private String apellido;

    @Schema(required = false)       // no es obligatorio ponerlo por que no todos los lectores representan una institución
    @Min(value = 1)
    private Long idInstitucion;

    @NotNull
    @Size(min = 8, max = 8, message = "Formato de número de teléfono incorrecto.")
    @Pattern(regexp = "\\d+", message = "Formato de número de teléfono incorrecto.")
    private String telefono;
    
}
