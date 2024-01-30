package com.example.Biblioteca.dto.PrestamoDto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoPrestamoIngreso {
    @NotNull
    private List<Long> libros;
    @NotNull
    private Long idLector;
    @NotNull
    private LocalDate fechaVencimiento;

}
