package com.example.Biblioteca.service.servicioLector;

import java.util.List;

import com.example.Biblioteca.dto.DtoLector;
import com.example.Biblioteca.dto.LectorDto.DtoLectorIngreso;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.service.IGenericService;

public interface ILectorService extends IGenericService<Lector>{
    Lector sevaLector(DtoLectorIngreso lec);
    List<Lector> mostrarPorInsitucion(Long idInstitucion);
    void actualizarLector(DtoLector lectorDto);
    
}
