package com.example.Biblioteca.service.servicioPrestamo;

import java.util.List;

import com.example.Biblioteca.dto.DtoPrestamo;
import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoIngreso;
import com.example.Biblioteca.modelo.Prestamo;

public interface IPrestamoService{
    public Prestamo guardarPrestamo(DtoPrestamoIngreso pres);
    public DtoPrestamo mostrarPrestamo(Long id);
    public List<DtoPrestamo> mostrarPrestamos();
    public List<DtoPrestamo> mostrarActivos(boolean activo);
    public void finalizarPrestamo(Prestamo prestamo);
    public void eliminarPrestamo(Long idPrestamo);
    public Prestamo obtenerPrestamo(Long idPrestamo);
    
}
