package com.example.Biblioteca.modelo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
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

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_institucion", referencedColumnName = "idInstitucion")
    @Cascade(CascadeType.PERSIST)
    @Valid
    private Institucion institucion;

    @NotNull
    @Size(min = 8, max = 8, message = "Formato de número de teléfono incorrecto.")
    @Pattern(regexp = "\\d+", message = "Formato de número de teléfono incorrecto.")
    private String numTelefono;

    public String nombreCompletoLector(){
        return this.nombre + " " + this.apellido;
    }
}
