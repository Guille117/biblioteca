package com.example.Biblioteca.modelo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLector;

    private String nombre;
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_institucion", referencedColumnName = "idInstitucion")
    @Cascade(CascadeType.PERSIST)
    private Institucion institucion;

    private String numTelefono;

    public String nombreCompletoLector(){
        return this.nombre + " " + this.apellido;
    }
}
