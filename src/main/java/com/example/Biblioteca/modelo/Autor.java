package com.example.Biblioteca.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;
    
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ]+$", message = "Los nombres no deben contener caracteres especiales")
    @NotNull
    private String nombre;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ]+$", message = "Los apellidos no deben contener caracteres especiales")
    @NotNull
    private String apellido;

    public String nombreCompleto(){
        return this.nombre + " " + this.apellido;
    }
}
