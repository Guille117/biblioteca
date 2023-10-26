package com.example.Biblioteca.dto;

import com.example.Biblioteca.modelo.Institucion;
import jakarta.validation.Valid;
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
public class DtoLector {
    @NotNull
    private Long idLector;

    private String nombre;

    private String apellido;

    @Valid
    private Institucion institucion;
    
    @Size(min = 8, max = 8, message = "Formato de número de teléfono incorrecto.")
    @Pattern(regexp = "\\d+", message = "Formato de número de teléfono incorrecto.")
    private String numTelefono;
}
