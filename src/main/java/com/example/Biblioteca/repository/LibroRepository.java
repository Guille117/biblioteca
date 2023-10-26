package com.example.Biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    public abstract Libro findByNombre(String nombre);
    public abstract Libro findByNombreAndEdicion(String nombre, int edicion);
    public abstract List<Libro> findByCategoriaIdCategoria(Long idCategoria);
    public abstract List<Libro> findByEditorialIdEditorial(Long idCategoria);
    public abstract List<Libro> findByAutorIdAutor(Long idAutor);
    public abstract List<Libro> findByEnPrestamoGreaterThan(Integer valor);
}
