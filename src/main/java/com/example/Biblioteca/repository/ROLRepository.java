package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.ROL;

@Repository
public interface ROLRepository extends JpaRepository<ROL, Long>{
    public abstract ROL findByNombre(String nombre);
}
