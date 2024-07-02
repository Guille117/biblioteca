package com.example.Biblioteca.Excepciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

@RestControllerAdvice
public class Excepciones {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> argumentoNoValido(MethodArgumentNotValidException e){
        List<Map<String, String>> errores = e.getFieldErrors().stream().map(error -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("causa", error.getField());
            errorMap.put("mensaje", error.getDefaultMessage());
            return errorMap;
        }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<?> excepcionValidacion(ValidacionException e){
        CustomError er = new CustomError(e.getCausa(), e.getMensaje());
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ElementoRepetidoException.class)
    public ResponseEntity<?> handleElementoRepetidoException(ElementoRepetidoException e){
        CustomError er = new CustomError(e.getCausa(), e.getMensaje());
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ElementoEnUsoException.class)
    public ResponseEntity<?> handleElementoEnUsoException(ElementoEnUsoException e){
        CustomError er = new CustomError(e.getCausa(), e.getMensaje());
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> noEncontrado(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accesoDenegado(){
        return ResponseEntity.status(403).body("Su usuario no cuenta con la autorización para realizar esta acción.");
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    public ResponseEntity<String> sesionExpirada(){
        return ResponseEntity.status(401).body("Sesión expirada, vuelva a iniciar sesión"); 
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> badRequest(){
        return ResponseEntity.status(400).body("La solicitud contiene errores, asegúrese de enviar el tipo de dato requerido y la correcta utilización de comas y comillas.");
    }

@Getter
    static class CustomError{
        private String causa;
        private String mensaje;

        public CustomError(String causa, String mensaje){
            this.causa = causa;
            this.mensaje = mensaje;
        }
    }
}
