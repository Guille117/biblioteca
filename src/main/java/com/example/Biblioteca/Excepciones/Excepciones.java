package com.example.Biblioteca.Excepciones;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class Excepciones {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DtoException>> argumentoNoValido(MethodArgumentNotValidException e){
        List<DtoException> errores = e.getFieldErrors().stream().map(wz -> 
                    new DtoException(wz.getField(), wz.getDefaultMessage())).collect(Collectors.toList());
    
        return ResponseEntity.badRequest().body(errores);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DtoException> excepcionValidacion(ValidationException e){
        DtoException error = new DtoException(e.getLocalizedMessage(), e.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ElementoRepetidoException.class)
    public ResponseEntity<DtoException> elementoRepetido(ElementoRepetidoException e){
        DtoException error = new DtoException(e.getCausa(), e.getMensaje());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> noEncontrado(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accesoDenegado(){
        return ResponseEntity.status(403).body("Este usuario no cuenta con las credenciales para acceder");
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    public ResponseEntity<String> sesionExpirada(){
        return ResponseEntity.status(401).body("Sesión expirada, vuelva a iniciar sesión"); 
    }

}
