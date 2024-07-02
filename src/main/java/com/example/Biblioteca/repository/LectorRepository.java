package com.example.Biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Lector;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long>{
    public abstract Lector findByNombreAndApellido(String nombre, String apellido);   
    public abstract List<Lector> findByInstitucionIdInstitucion(Long idInstitucion);
    public abstract boolean existsByInstitucionIdInstitucion(Long idInsti);
    public abstract boolean existsByNombreAndApellido(String nombre, String apellido);
}
