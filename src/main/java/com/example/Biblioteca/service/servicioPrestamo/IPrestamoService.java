package com.example.Biblioteca.service.servicioPrestamo;

import java.util.List;

import com.example.Biblioteca.dto.DtoPrestamo;
import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.service.IGenericService;

public interface IPrestamoService extends IGenericService<Prestamo>{
    public DtoPrestamo mostrarPrestamo(Long id);
    public List<DtoPrestamo> mostrarPrestamos();
    public void finalizarPrestamo(Prestamo prestamo);
    public List<DtoPrestamo> mostrarActivos(boolean activo);
}
