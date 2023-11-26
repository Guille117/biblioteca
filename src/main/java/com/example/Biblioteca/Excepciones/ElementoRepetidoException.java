package com.example.Biblioteca.Excepciones;

import lombok.Getter;

@Getter
public class ElementoRepetidoException extends RuntimeException{
    private String causa;
    private String mensaje;

    public ElementoRepetidoException(String causa, String mensaje){
        this.causa = causa;
        this.mensaje = mensaje;
    }
    

}
