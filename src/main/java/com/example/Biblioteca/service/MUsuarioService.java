package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.MUsuario;
import com.example.Biblioteca.repository.MUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class MUsuarioService implements IGenericService<MUsuario>{
    @Autowired
    private MUsuarioRepository usuRepo;
    @Autowired
    private PasswordEncoder enco;
    @Autowired
    private RolService rolserv;

    @Override
    public void guardar(MUsuario usu) {// si se agrega otra validación, crear una clase para validar MUsuario  
        if(usuRepo.existsByAlias(usu.getAlias())){    
            throw new ElementoRepetidoException("alias","Este alias de usuario ya está en uso");
        }else{
            usu.setRol(rolserv.obtenerUno(usu.getRol().getIdRol()));
            usu.setContraseña(enco.encode(usu.getContraseña()));

            usuRepo.save(usu);
        }
    }

    @Override
    public MUsuario obtenerUno(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return usuRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
    }

    @Override
    public List<MUsuario> obtenerTodos() {
       return usuRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        usuRepo.deleteById(id);
    }
}
