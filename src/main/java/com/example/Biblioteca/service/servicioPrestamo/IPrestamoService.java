package com.example.Biblioteca.service.servicioPrestamo;

import java.util.List;

import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoMostrar;
import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoIngreso;
import com.example.Biblioteca.modelo.Prestamo;

public interface IPrestamoService{
    public Prestamo guardarPrestamo(DtoPrestamoIngreso pres);
    public DtoPrestamoMostrar mostrarPrestamo(Long id);
    public List<DtoPrestamoMostrar> mostrarPrestamos();
    public List<DtoPrestamoMostrar> mostrarActivos(boolean activo);
    public void finalizarPrestamo(Prestamo prestamo);
    public void eliminarPrestamo(Long idPrestamo);
    public Prestamo obtenerPrestamo(Long idPrestamo);
    
}
