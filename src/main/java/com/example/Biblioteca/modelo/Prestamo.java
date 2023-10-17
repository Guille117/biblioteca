package com.example.Biblioteca.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    @NotNull
    @OneToMany
    @JoinColumn(name = "id_libro")
    private List<Libro> libros;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_lector", referencedColumnName = "idLector")
    private Lector lector;

    private LocalDate fechaInicio = LocalDate.now();

    @Future
    @NotNull
    private LocalDate fechaVencimiento;

    private boolean prestamoActivo = true;
    
}
