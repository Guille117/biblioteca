package com.example.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.modelo.MUsuario;

@Repository
public interface MUsuarioRepository extends JpaRepository<MUsuario, Long>{
    public abstract MUsuario findByAlias(String alias);
    public abstract boolean existsByAlias(String alias);
}
