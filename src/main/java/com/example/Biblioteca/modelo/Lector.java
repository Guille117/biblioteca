package com.example.Biblioteca.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private Institucion institucion;

    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "\\d+")
    private String numTelefono;
}
