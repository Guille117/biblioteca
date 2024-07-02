package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Institucion;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Long>{
    public abstract boolean existsByNombre(String nombreInstitucion);
    
}
