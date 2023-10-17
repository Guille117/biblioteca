package com.example.Biblioteca.service.servicioLibro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.modelo.Editorial;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.LibroRepository;
import com.example.Biblioteca.service.IGenericService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LibroService implements ILibroService{
    @Autowired
    private LibroRepository libRepo;
    @Autowired
    private IGenericService<Autor> autorService;
    @Autowired
    private IGenericService<Editorial> editorialService;
    @Autowired
    private IGenericService<Categoria> categoriaService;


    @Override
    public void guardar(Libro t) {
        if(t.getAutor().getIdAutor() != null){
            t.setAutor(autorService.obtenerUno(t.getAutor().getIdAutor()));
        }

        if(t.getCategoria().getIdCategoria() != null){
            t.setCategoria(categoriaService.obtenerUno(t.getCategoria().getIdCategoria()));
        }

        if(t.getEditorial().getIdEditorial() != null){
            t.setEditorial(editorialService.obtenerUno(t.getEditorial().getIdEditorial()));
        }
        
        
        libRepo.save(t);
    }

    @Override
    public Libro obtenerUno(Long id) {
        return libRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado."));
    }

    @Override
    public List<Libro> obtrnerTodos() {
        return libRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        libRepo.deleteById(id);
    }

    @Override
    public Libro obtenerPorNombre(String nombre) {
       return libRepo.findByNombre(nombre);
    }
    
}
