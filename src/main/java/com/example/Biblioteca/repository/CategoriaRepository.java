package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.modelo.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    // retorna true si ya existe una categoría con el nombre ingresado
    public abstract boolean existsByNombre(String nombre);
}
