package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.modelo.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
    
}
