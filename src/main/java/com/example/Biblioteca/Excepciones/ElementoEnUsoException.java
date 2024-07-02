package com.example.Biblioteca.Excepciones;

public class ElementoEnUsoException extends DtoException{
    public ElementoEnUsoException(String causa, String mensaje){
        super(causa, mensaje);
    }
}
