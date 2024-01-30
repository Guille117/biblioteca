package com.example.Biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DtoMUsuario {
    @NotNull
    @Size(max = 40)
    private String alias;

    @NotNull
    private String contraseña;
}
