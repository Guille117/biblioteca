package com.example.Biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    public abstract Libro findByNombre(String nombre);
    public abstract List<Libro> findByCategoriaIdCategoria(Long idCategoria);
    public abstract List<Libro> findByEditorialIdEditorial(Long idEditorial);
    public abstract List<Libro> findByAutorIdAutor(Long idAutor);
    public abstract List<Libro> findByEnPrestamoGreaterThan(Integer valor); // retorna todos los resultados cuyos "EnPrestamo" sea mayor a "valor"

    // validadores para otras clases
    public abstract boolean existsByAutorIdAutor(Long idAutor);
    public abstract boolean existsByCategoriaIdCategoria(Long idCategoria);
    public abstract boolean existsByEditorialIdEditorial(Long idEditorial);
    public abstract boolean existsByNombreAndEdicionAndAutorIdAutor(String nombre, Integer edicion, Long idAutor);
    public abstract boolean existsByIdLibroAndDisponibleGreaterThan(Long idLibro, Integer valor);
}
