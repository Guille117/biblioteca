package com.example.Biblioteca.service.servicioLibro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.dto.DtoLibro;
import com.example.Biblioteca.modelo.Autor;
import com.example.Biblioteca.modelo.Categoria;
import com.example.Biblioteca.modelo.Editorial;
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
    public void guardar(Libro t) {
        validadores.forEach(v -> v.validar(t));
        
        Autor autor = (t.getAutor().getIdAutor() != null) ? 
                autorService.obtenerUno(t.getAutor().getIdAutor()) :
                autorService.buscarNombre(t.getAutor().getNombre(), t.getAutor().getApellido());

        if(autor != null) t.setAutor(autor);

        Categoria categoria = (t.getCategoria().getIdCategoria() != null) ?
                categoriaService.obtenerUno(t.getCategoria().getIdCategoria()) :
                categoriaService.buscarNombre(t.getCategoria().getNombre());

        if(categoria != null) t.setCategoria(categoria);

        Editorial editorial = (t.getEditorial().getIdEditorial() != null) ? 
                editorialService.obtenerUno(t.getEditorial().getIdEditorial()) :
                editorialService.buscarNombre(t.getEditorial().getNombre());

        if(editorial != null) t.setEditorial(editorial);
        
        t.iniciarDisponible();
        existencia.validar(t);
        libRepo.save(t);
    }

    @Override
    public Libro obtenerUno(Long id) {
        return libRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado."));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libRepo.findAll();
    }       

    @Override
    public List<Libro> MostrarPorCategoria(Long idCategoria) {
        return libRepo.findByCategoriaIdCategoria(idCategoria);
    }

    @Override
    public List<Libro> MostrarPorEditorial(Long idEditorial) {
        return libRepo.findByEditorialIdEditorial(idEditorial);
    }

    @Override
    public List<Libro> MostrarPorAutor(Long idAutor) {
        return libRepo.findByAutorIdAutor(idAutor);
    }

    @Override
    public List<Libro> MostrarEnPrestamo() {
        return libRepo.findByEnPrestamoGreaterThan(0);
    }

        @Override
    public void eliminar(Long id) {
        Libro lib = this.obtenerUno(id);
        if(lib.getEnPrestamo() == 0){
            libRepo.deleteById(id);
        }else{
            throw new ValidationException("Para eliminar un libro, esto no debe estar en prestamo.");
        }
    }

    
    @Override
    public void actualizarLibro(DtoLibro lib) {
        Libro libro = this.obtenerUno(lib.getIdLibro());
       
        Long idPivote = null;
        String nomPivote = null;
        String apePivote = null;

        Libro libroPivote = convertirALibro(lib);
        validadores.forEach(v -> v.validar(libroPivote));

        if(lib.getAutor() != null){
            idPivote = lib.getAutor().getIdAutor();
            nomPivote = lib.getAutor().getNombre();
            apePivote = lib.getAutor().getApellido();

            Autor autor = (idPivote != null) ? autorService.obtenerUno(idPivote) : autorService.buscarNombre(nomPivote, apePivote);

            if(autor == null && nomPivote != null && apePivote != null){
                autor = lib.getAutor();
                autorService.guardar(autor);
            }

            libro.setAutor(autor);
        }


        if(lib.getCategoria() != null){
            idPivote = lib.getCategoria().getIdCategoria();
            nomPivote = lib.getCategoria().getNombre();

            Categoria categoria = (idPivote != null) ? categoriaService.obtenerUno(idPivote) : categoriaService.buscarNombre(nomPivote);

            if(categoria == null && nomPivote != null){
                categoria = lib.getCategoria();
                categoriaService.guardar(categoria);
            }

            libro.setCategoria(categoria);
        }


        if(lib.getEditorial() != null){
            idPivote = lib.getEditorial().getIdEditorial();
            nomPivote = lib.getEditorial().getNombre();

            Editorial editorial = (idPivote != null) ? editorialService.obtenerUno(idPivote) : editorialService.buscarNombre(nomPivote);

            if(editorial == null && nomPivote != null){
                editorial = lib.getEditorial();
                editorialService.guardar(editorial);
            }

            libro.setEditorial(editorial);
        }
        
        if(lib.getNombre() != null){
            libro.setNombre(lib.getNombre());
        }
        if(lib.getAnioPublicacion() != null){
            libro.setAnioPublicacion(lib.getAnioPublicacion());
        }
        if(lib.getCantidad() != null){
            libro.iniciarDisponible();
            libro.setCantidad(lib.getCantidad());
        }
        if(lib.getEdicion() != null){
            libro.setEdicion(lib.getEdicion());
        }

        libRepo.save(libro);
    }


        public Libro convertirALibro(DtoLibro libroDto){
        Libro lib = new Libro(null, libroDto.getNombre(), 
                libroDto.getAutor(), libroDto.getCategoria(), 
                libroDto.getEditorial(), libroDto.getAnioPublicacion(), 
                libroDto.getEdicion(), libroDto.getCantidad(), 
    null, null, null);
        
        return lib;
    }

}
