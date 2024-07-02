package com.example.Biblioteca.dto.PrestamoDto;

import com.example.Biblioteca.modelo.Lector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LectorPresDto {
    private Long idLector;
    private String nombre;
    private String telefono;

    // atributos del lector que se mostrarán en un préstamo
    public LectorPresDto(Lector lec){
        this.idLector = lec.getIdLector();
        this.nombre = lec.nombreCompletoLector();
        this.telefono = lec.getNumTelefono();
    }
}
