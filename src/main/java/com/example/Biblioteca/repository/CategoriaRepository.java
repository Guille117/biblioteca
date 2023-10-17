package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.modelo.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    public abstract Categoria findByNombre(String nombreCategoria);
}
