package com.example.Biblioteca.dto.LectorDto;

import com.example.Biblioteca.modelo.Lector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DtoLectorMostrar {
    private Long idLector;
    private String nombre;
    private String telefono;
    private Long idInstitucion;
    private String nombreInsitucion;

    public DtoLectorMostrar(Lector l){
        this.idLector = l.getIdLector();
        this.nombre = l.nombreCompletoLector();

        this.telefono = l.getNumTelefono();

        if(l.getInstitucion() != null){
            this.idInstitucion = l.getInstitucion().getIdInstitucion();
            this.nombreInsitucion = l.getInstitucion().getNombre();
        }else{
            this.idInstitucion = null;
            this.nombreInsitucion = null;
        }
        
    }
}
