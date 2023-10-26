package com.example.Biblioteca.service.servicioLibro;

import java.util.List;

import com.example.Biblioteca.dto.DtoLibro;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.service.IGenericService;

public interface ILibroService extends IGenericService<Libro>{
    public void actualizarLibro(DtoLibro lib);
    public List<Libro> MostrarPorCategoria(Long idCategoria);
    public List<Libro> MostrarPorEditorial(Long idEditorial);
    public List<Libro> MostrarPorAutor(Long idAutor);
    public List<Libro> MostrarEnPrestamo();
}
