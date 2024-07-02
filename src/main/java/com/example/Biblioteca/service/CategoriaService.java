package com.example.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ElementoEnUsoException;
import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.repository.CategoriaRepository;
import com.example.Biblioteca.repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements IGenericService<Categoria>{

    @Autowired
    private CategoriaRepository catRepo;
    @Autowired
    private LibroRepository libRepo;

    @Override
    public void guardar(Categoria t) {
        if(!catRepo.existsByNombre(t.getNombre())){     // si se agrega otra validación, crear una clase especial para validar categoría
            catRepo.save(t);
        }else{
            throw new ElementoRepetidoException("Nombre", "Existe un registro de esta categoría");
        }
    }

    @Override
    public Categoria obtenerUno(Long id) {
        if(id!=null){           // No es necesario agregar el if, solo se usa para evitar la advertencia
            return catRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada."));
        }else{
            return null;
        }
       
    }

    @Override
    public List<Categoria> obtenerTodos() {
       return catRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if(id != null){
            if(libRepo.existsByCategoriaIdCategoria(id)){
                throw new ElementoEnUsoException("id", "No es posible eliminar esta categoría, está siendo referenciado en la sección de libros.");
            } else{
                this.obtenerUno(id);                // en caso no exista nos arroje una exception ya definida en el metodo obtener 1
                catRepo.deleteById(id);                
            }
        } 
    }
}
