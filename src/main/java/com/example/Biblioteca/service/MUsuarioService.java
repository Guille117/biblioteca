package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.MUsuario;
import com.example.Biblioteca.modelo.ROL;
import com.example.Biblioteca.repository.MUsuarioRepository;
import com.example.Biblioteca.repository.ROLRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class MUsuarioService implements IGenericService<MUsuario>{
    @Autowired
    private MUsuarioRepository usuRepo;
    @Autowired
    private PasswordEncoder enco;
    @Autowired
    private ROLRepository rolRepo;

    @Override
    public void guardar(MUsuario usu) {
        if(usuRepo.existsByAlias(usu.getAlias())){
            throw new ElementoRepetidoException("alias","Este alias de usuario ya está en uso");
        }else{
            ROL rol = (usu.getRol().getIdRol() != null) ? 
                rolRepo.findById(usu.getRol().getIdRol()).orElse(null) :
                rolRepo.findByNombre(usu.getRol().getNombre()); 

            usu.setContraseña(enco.encode(usu.getContraseña()));

            if(rol != null) usu.setRol(rol);

            usuRepo.save(usu);
        }

        
    }

    @Override
    public MUsuario obtenerUno(Long id) {
        return usuRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
    }

    @Override
    public List<MUsuario> obtenerTodos() {
       return usuRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        usuRepo.deleteById(id);
    }
}
