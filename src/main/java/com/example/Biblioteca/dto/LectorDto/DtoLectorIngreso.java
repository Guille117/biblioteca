package com.example.Biblioteca.dto.LectorDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoLectorIngreso {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Los nombres no deben contener caracteres especiales")
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Los apellidos no deben contener caracteres especiales")
    private String apellido;

    @Schema(required = false)
    @Pattern(regexp = "\\d+", message = "Debe ingresar un número entero positivo.")
    private Long idIstitucion;

    @NotNull
    @Size(min = 8, max = 8, message = "Formato de número de teléfono incorrecto.")
    @Pattern(regexp = "\\d+", message = "Formato de número de teléfono incorrecto.")
    private String telefono;
    
}
