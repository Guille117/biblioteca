package com.example.Biblioteca.Excepciones;

public class ValidacionException extends DtoException{
    
    public ValidacionException(String causa, String mensaje){
        super(causa,mensaje);
    }
}
