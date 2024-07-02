package com.example.Biblioteca.dto.LectorDto;

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
public class DtoLectorModificar {
    @NotNull
    private Long idLector;

    private String nombre;

    private String apellido;

    private Long idInstitucion;
    
    @Size(min = 8, max = 8, message = "Formato de número de teléfono incorrecto.")
    @Pattern(regexp = "\\d+", message = "Formato de número de teléfono incorrecto.")
    private String numTelefono;
}
