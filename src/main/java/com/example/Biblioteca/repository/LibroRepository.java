package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    public abstract Libro findByNombre(String nombreLibro);
}
