package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.modelo.Editorial;


@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>{
    public abstract Editorial findByNombre(String nombreEditorial);
}
