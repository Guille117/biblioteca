package com.example.Biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Biblioteca.modelo.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
    public abstract boolean existsByLectorIdLectorAndPrestamoActivo(Long idLector, boolean prestamoActivo);
    public abstract boolean existsByIdPrestamoAndPrestamoActivoTrue(Long idPrestamo);
    public abstract boolean existsByLectorIdLector(Long idLector);
    public abstract List<Prestamo> findByPrestamoActivo(boolean activo);
}
