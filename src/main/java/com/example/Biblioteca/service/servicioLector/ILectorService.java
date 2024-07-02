package com.example.Biblioteca.service.servicioLector;

import java.util.List;

import com.example.Biblioteca.dto.LectorDto.DtoLectorModificar;
import com.example.Biblioteca.dto.LectorDto.DtoLectorMostrar;
import com.example.Biblioteca.dto.LectorDto.DtoLectorIngreso;
import com.example.Biblioteca.modelo.Lector;

public interface ILectorService{
    public DtoLectorMostrar GuardarLector(DtoLectorIngreso lec);            // se usa para mostrar
    public DtoLectorMostrar obtenerUno(Long id);                            // se usa para mostrar
    public List<DtoLectorMostrar> obtenerTodos();                          // se usa para mostrar
    public void eliminar(Long id);
    public List<DtoLectorMostrar> mostrarPorInsitucion(Long idInstitucion); // se usa para mostrar
    public void actualizarLector(DtoLectorModificar lectorDto);             // se usa para modificar
    public Lector Retorna1Lector(Long id);                                  // se usará en otras clases que requiera un Lector
    
}
