package com.example.Biblioteca.Excepciones;

import lombok.Getter;

@Getter
public class ElementoRepetidoException extends DtoException{


    public ElementoRepetidoException(String causa, String mensaje){
        super(causa, mensaje);
    }

}
