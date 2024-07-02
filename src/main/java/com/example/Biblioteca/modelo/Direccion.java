package com.example.Biblioteca.modelo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Embeddable
public class Direccion {
    @Min(value = 1)
    @NotNull
    private Integer numCalle;
    
    @Min(value = 1)
    @NotNull
    private Integer numAvenida;

}
