package com.example.Biblioteca.service.servicioLibro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.dto.LibroDto.DtoLibro;
import com.example.Biblioteca.dto.LibroDto.DtoLibroIngreso;
import com.example.Biblioteca.dto.LibroDto.DtoLibroMostrar;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.repository.LibroRepository;
import com.example.Biblioteca.service.AutorService;
import com.example.Biblioteca.service.CategoriaService;
import com.example.Biblioteca.service.EditorialService;
import com.example.Biblioteca.validacion.ValidacionLibro.IValidarLibro;
import com.example.Biblioteca.validacion.ValidacionLibro.ValidarExistencia;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;


@Service
public class LibroService implements ILibroService{
    @Autowired
    private LibroRepository libRepo;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private List<IValidarLibro> validadores;
    @Autowired
    private ValidarExistencia existencia;


    @Override
    public Libro guardar(DtoLibroIngreso t) {
        Libro libro = convertirALibro(t);
        validadores.forEach(v -> v.validar(libro));

        libro.setAutor(autorService.obtenerUno(libro.getAutor().getIdAutor()));
        libro.setCategoria(categoriaService.obtenerUno(libro.getCategoria().getIdCategoria()));
        libro.setEditorial(editorialService.obtenerUno(libro.getEditorial().getIdEditorial()));

        existencia.validar(libro);
        libRepo.save(libro);
        return libro;
    }

    @Override       // Obtiene y retorna un libro, se usa especialmente en el módulo de préstamo
    public Libro obtenerUnoLibro(Long id){
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return libRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));
    }
    
    @Override       // obtiene un libro pero retorna un dto de libro, se usa para mostrar el libro
    public DtoLibroMostrar obtenerUno(Long id) {
        Libro l1 = this.obtenerUnoLibro(id);
        return new DtoLibroMostrar(l1);
    }

    @Override
    public List<DtoLibroMostrar> obtenerTodos() {
        List<Libro> lib1 = libRepo.findAll();
        return convertirADtoLibroMostrar(lib1);
    }       

    @Override
    public List<DtoLibroMostrar> MostrarPorCategoria(Long idCategoria) {
        List<Libro> libPivote = libRepo.findByCategoriaIdCategoria(idCategoria);
        return convertirADtoLibroMostrar(libPivote);
    }

    @Override
    public List<DtoLibroMostrar> MostrarPorEditorial(Long idEditorial) {
        List<Libro> libPivote = libRepo.findByEditorialIdEditorial(idEditorial);
        return convertirADtoLibroMostrar(libPivote); 
    }

    @Override
    public List<DtoLibroMostrar> MostrarPorAutor(Long idAutor) {
        List<Libro> libPivote = libRepo.findByAutorIdAutor(idAutor);
        return convertirADtoLibroMostrar(libPivote); 
    }

    @Override
    public List<DtoLibroMostrar> MostrarEnPrestamo() {
        List<Libro> libPivote = libRepo.findByEnPrestamoGreaterThan(0);
        return convertirADtoLibroMostrar(libPivote); 
    }

    @Override
    public void eliminar(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }else{
            DtoLibroMostrar lib = this.obtenerUno(id);
            if(lib.enPrestamo() == 0){
                libRepo.deleteById(id);
            }else{
                throw new ValidationException("Para eliminar un libro, esto no debe estar en prestamo.");
        }
        }
        
    }

    
    @Override
    public void actualizarLibro(DtoLibro lib) {
        
        Libro libro = libRepo.findById(lib.getIdLibro()).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado."));

        if(lib.getNombre() != null){
            libro.setNombre(lib.getNombre());
        }

        if(lib.getAutor() != null){
            libro.setAutor(autorService.obtenerUno(lib.getAutor().getIdAutor()));
        }

        if(lib.getCategoria() != null){
            libro.setCategoria(categoriaService.obtenerUno(lib.getCategoria().getIdCategoria()));
        }

        if(lib.getEditorial() != null){
            libro.setEditorial(editorialService.obtenerUno(lib.getEditorial().getIdEditorial()));
        }

        if(lib.getAnioPublicacion() != null){
            libro.setAnioPublicacion(lib.getAnioPublicacion());
        }

        if(lib.getEdicion() != null){
            libro.setEdicion(lib.getEdicion());
        }

        if(lib.getCantidad() != null){
            libro.setCantidad(lib.getCantidad());
        }

        validadores.forEach(v -> v.validar(libro));
        libRepo.save(libro);
    }

    private List<DtoLibroMostrar> convertirADtoLibroMostrar(List<Libro> libros){
        List<DtoLibroMostrar> libsF = libros.stream().map(DtoLibroMostrar::new).collect(Collectors.toList());

        return libsF;
    }

    private Libro convertirALibro(DtoLibroIngreso libroDto){
        Libro lib = new Libro();

        lib.setNombre(libroDto.getNombre());
        lib.setAutor(autorService.obtenerUno(libroDto.getIdAutor()));
        lib.setCategoria(categoriaService.obtenerUno(libroDto.getIdCategoria()));
        lib.setEditorial(editorialService.obtenerUno(libroDto.getIdEditorial()));
        lib.setEdicion(libroDto.getEdicion());
        lib.setAnioPublicacion(libroDto.getAnioPublicacion());
        lib.setCantidad(libroDto.getCantidad());
        lib.setDisponible(libroDto.getCantidad());      // seteamos el valor de cantidad a disponible por que al inicio disponible es igual a cantidad
        
        return lib;
    }
}
