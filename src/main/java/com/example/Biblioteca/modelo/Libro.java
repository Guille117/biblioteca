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

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "idAutor")
    @Cascade(CascadeType.PERSIST)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria")
    @Cascade(CascadeType.PERSIST)
    private Categoria categoria; 

    @ManyToOne
    @JoinColumn(name = "id_editorial", referencedColumnName = "idEditorial")
    @Cascade(CascadeType.PERSIST)
    private Editorial editorial;

    private String anioPublicacion;
    private Integer edicion;
    private Integer cantidad;
    private Integer disponible;
    private Integer enPrestamo = 0;
    private LocalDate fechaIngreso = LocalDate.now();

}
