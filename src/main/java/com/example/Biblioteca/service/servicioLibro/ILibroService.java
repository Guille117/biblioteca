package com.example.Biblioteca.service.servicioLibro;

import java.util.List;

import com.example.Biblioteca.dto.LibroDto.DtoLibro;
import com.example.Biblioteca.dto.LibroDto.DtoLibroIngreso;
import com.example.Biblioteca.dto.LibroDto.DtoLibroMostrar;
import com.example.Biblioteca.modelo.Libro;

public interface ILibroService{
    public Libro guardar(DtoLibroIngreso t);
    public DtoLibroMostrar obtenerUno(Long idLibro);
    public Libro obtenerUnoLibro(Long idLibro);
    public List<DtoLibroMostrar> obtenerTodos();
    public List<DtoLibroMostrar> MostrarPorCategoria(Long idCategoria);
    public List<DtoLibroMostrar> MostrarPorEditorial(Long idEditorial);
    public List<DtoLibroMostrar> MostrarPorAutor(Long idAutor);
    public List<DtoLibroMostrar> MostrarEnPrestamo();
    public void actualizarLibro(DtoLibro lib);
    public void eliminar(Long id);
    

}
