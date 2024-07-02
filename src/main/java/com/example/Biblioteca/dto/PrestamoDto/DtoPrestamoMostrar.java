package com.example.Biblioteca.dto.PrestamoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Biblioteca.modelo.Prestamo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DtoPrestamoMostrar {
    private Long idPrestamo;
    private List<LibroPresDto> libros;
    private LectorPresDto lector;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private boolean Activo;

    public DtoPrestamoMostrar(Prestamo pres){
        this.idPrestamo = pres.getIdPrestamo();
        this.libros = pres.getLibros().stream().map(LibroPresDto::new).collect(Collectors.toList());
        this.lector = new LectorPresDto(pres.getLector());
        this.fechaInicio = pres.getFechaInicio();
        this.fechaVencimiento = pres.getFechaVencimiento();
        this.Activo = pres.isPrestamoActivo();
    }
}
