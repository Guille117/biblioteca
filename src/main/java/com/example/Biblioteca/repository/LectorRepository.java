package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Lector;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long>{
    public abstract Lector findByNombre(String nombreLector);   
}
