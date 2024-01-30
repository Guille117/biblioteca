package com.example.Biblioteca.validacion;

public class UtilidadesValidacion {
    public static boolean validarNull(Object o, String mensajeError){
        if(o != null){
            return true;
        }else{
            throw new IllegalStateException(mensajeError);
        }
    }
}
