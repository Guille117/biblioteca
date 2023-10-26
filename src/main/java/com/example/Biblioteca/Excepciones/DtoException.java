package com.example.Biblioteca.Excepciones;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DtoException {
    private String causa;
    private String mensaje;

    public DtoException(String causa, String mensaje){
        this.causa = causa;
        this.mensaje = mensaje;
    }
}
