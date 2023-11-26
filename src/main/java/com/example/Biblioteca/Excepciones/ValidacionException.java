package com.example.Biblioteca.Excepciones;

public class ValidacionException extends ElementoRepetidoException{
    
    public ValidacionException(String causa, String mensaje){
        super(causa,mensaje);
    }
}
