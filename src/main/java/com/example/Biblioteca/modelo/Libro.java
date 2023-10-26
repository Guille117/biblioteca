package com.example.Biblioteca.modelo;


import java.time.LocalDate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @NotNull
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "idAutor")
    @Cascade(CascadeType.PERSIST)
    private Autor autor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria")
    @Cascade(CascadeType.PERSIST)
    private Categoria categoria; 

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_editorial", referencedColumnName = "idEditorial")
    @Cascade(CascadeType.PERSIST)
    private Editorial editorial;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "\\d+")
    private String anioPublicacion;

    @NotNull
    private Integer edicion;

    @NotNull
    @Min(value =  0)
    @Max(value =  7)
    private Integer cantidad;

    private Integer disponible;

    private Integer enPrestamo = 0;

    private LocalDate fechaIngreso = LocalDate.now();


    public void iniciarDisponible(){
        this.disponible = this.cantidad;
    }

}
