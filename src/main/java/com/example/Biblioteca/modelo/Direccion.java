package com.example.Biblioteca.modelo;

import jakarta.persistence.Embeddable;
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
    @NotNull
    private String NumCalle;
    
    @NotNull
    private String NumAvenida;

}
