package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Autor;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    public abstract Autor findByNombre(String nombreAutor);
}
